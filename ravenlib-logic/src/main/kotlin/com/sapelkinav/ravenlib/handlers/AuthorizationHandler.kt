package com.sapelkinav.ravenlib.handlers

import com.sapelkinav.ravenlib.client.TdlibParameters
import com.sapelkinav.ravenlib.exception.AuthorisationException
import io.reactivex.subjects.Subject
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi


class AuthorizationHandler(
    val client: Client,
    val tdlibParameters: TdlibParameters,
    val authorizationEvents: Subject<TdApi.Object>,
    val phone: String,
    val getCode: () -> String,
    val getPassword: () -> String
) {
    var isAuthorised = false
    private lateinit var authorizationState: TdApi.AuthorizationState


    init {
        authorizationEvents
            .filter { event: TdApi.Object ->
                event.constructor == TdApi.UpdateAuthorizationState.CONSTRUCTOR
            }
            .map { event ->
                (event as TdApi.UpdateAuthorizationState).authorizationState
            }
            .subscribe { authState ->
                if (authState != null) {
                    authorizationState = authState
                }
                when (authorizationState.constructor) {

                    TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR ->
                        client.send(TdApi.SetTdlibParameters(tdlibParameters.convertToTdlibFormat()), ::authHandler)

                    TdApi.AuthorizationStateWaitEncryptionKey.CONSTRUCTOR ->
                        client.send(TdApi.CheckDatabaseEncryptionKey(), ::authHandler)

                    TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR ->
                        client.send(TdApi.SetAuthenticationPhoneNumber(phone, false, false), ::authHandler)

                    TdApi.AuthorizationStateWaitCode.CONSTRUCTOR ->
                        client.send(TdApi.CheckAuthenticationCode(getCode(), "", ""), ::authHandler)

                    TdApi.AuthorizationStateWaitPassword.CONSTRUCTOR ->
                        client.send(TdApi.CheckAuthenticationPassword(getPassword()), ::authHandler)


                    TdApi.AuthorizationStateReady.CONSTRUCTOR ->
                        isAuthorised = true

                    TdApi.AuthorizationStateLoggingOut.CONSTRUCTOR -> {
                        isAuthorised = false
                        print("Logging out")
                    }

                    TdApi.AuthorizationStateClosing.CONSTRUCTOR -> {
                        isAuthorised = false
                        print("Closed")
                    }
                    else -> System.err.println("Unsupported authorization state: \n $authorizationState")
                }
            }

    }

}

fun authHandler(tdEvent: TdApi.Object?) {
    if (tdEvent != null) {
        when (tdEvent.constructor) {
            TdApi.Error.CONSTRUCTOR -> {
                val error = tdEvent as TdApi.Error
                println("Receive an error: $error")
                throw AuthorisationException(error.code, error.message)
            }
            TdApi.Ok.CONSTRUCTOR -> { }
            else -> System.err.println("Receive wrong response from TDLib: $tdEvent")
        }
    }
}







