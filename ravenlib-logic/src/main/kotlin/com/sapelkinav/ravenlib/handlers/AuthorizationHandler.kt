package com.sapelkinav.ravenlib.handlers

import com.sapelkinav.ravenlib.client.TdlibParameters
import io.reactivex.subjects.BehaviorSubject
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi


class AuthorizationHandler(val client:Client,
                           val tdlibParameters: TdlibParameters,
                           val authorizationEvents: BehaviorSubject<TdApi.Object>,
                           val phone:String,
                           val getCode:()->String,
                           val getPassword:()->String) {
    var isAuthorised = false
    private lateinit var authorizationState:TdApi.AuthorizationState


    init {
        authorizationEvents
                .filter {
                    event: TdApi.Object ->
                    event.constructor == TdApi.UpdateAuthorizationState.CONSTRUCTOR
                }
                .map {
                    event-> (event as TdApi.UpdateAuthorizationState).authorizationState
                }
                .subscribe{ authState->
                    if(authState != null){
                        authorizationState = authState
                    }
                    when(authorizationState.constructor){

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

                        TdApi.AuthorizationStateLoggingOut.CONSTRUCTOR ->{
                            isAuthorised = false
                            print("Logging out")
                        }

                        TdApi.AuthorizationStateClosing.CONSTRUCTOR ->{
                            isAuthorised = false
                            print("Closed")
                        }

                        else -> System.err.println("Unsupported authorization state: \n $authorizationState")

                    }
                }

    }

}
fun authHandler(obj: TdApi.Object?){
    if (obj != null) {
        when (obj.getConstructor()) {
            TdApi.Error.CONSTRUCTOR -> {
                System.err.println("Receive an error: $obj")
                // repeat last action

            }
            TdApi.Ok.CONSTRUCTOR -> {
            }
            else -> System.err.println("Receive wrong response from TDLib: $obj")
        }
    }
}







