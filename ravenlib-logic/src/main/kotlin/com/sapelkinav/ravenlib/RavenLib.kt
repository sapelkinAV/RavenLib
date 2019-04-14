package com.sapelkinav.ravenlib

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.client.TdlibParameters
import com.sapelkinav.ravenlib.handlers.AuthorizationHandler
import com.sapelkinav.ravenlib.handlers.UpdatesHandler
import com.sapelkinav.ravenlib.model.chat.Chat
import com.sapelkinav.ravenlib.model.chat.ChatRepository
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import org.scijava.nativelib.NativeLoader
import java.io.IOException


class RavenLib(
    val tdlibParameters: TdlibParameters,
    val phone: String,
    val getCode: () -> String,
    val getPassword: () -> String
) : AutoCloseable {
    init {
        try {
            NativeLoader.loadLibrary("tdjni")
        } catch (e: IOException) {
            e.printStackTrace()
            try {
                System.loadLibrary("tdjni")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }

    private val defaultErrorHandler = Client.ExceptionHandler { error ->
        errorEvents.onNext(error)
    }

    val tdEvents = PublishSubject.create<TdApi.Object>()
    val authorizationEvents: ReplaySubject<TdApi.Object> = ReplaySubject.create()
    val errorEvents: Subject<Throwable> = PublishSubject.create()
    val updatesHandler = UpdatesHandler(tdEvents, authorizationEvents, errorEvents)

    val client = Client.create(
        updatesHandler,
        defaultErrorHandler,
        defaultErrorHandler
    )
    private val authorizationHandler = AuthorizationHandler(
        client,
        tdlibParameters,
        authorizationEvents,
        phone,
        getCode,
        getPassword
    )

    val ravenClient = RavenClient(client)

    init {
        waitUntilAuthorizationStateReady()
    }
    val chatRepository = ChatRepository(ravenClient, errorEvents)

    override fun close() {
        ravenClient.tdCall(TdApi.Close()) {}
    }

    fun getChats(): List<Chat> {
        return chatRepository.getChatList()
    }

    fun getSupergroupChats(): List<Chat> {
        return chatRepository.getSecretChats()
    }

    fun getBasicGroupChats(): List<Chat> {
        return chatRepository.getBasicGroupChats()
    }

    fun getPrivateChats(): List<Chat> {
        return chatRepository.getPrivateChats()
    }

    private fun waitUntilAuthorizationStateReady() {

        val authStateReady = authorizationEvents.filter { tdEvent ->
            if (tdEvent.constructor == TdApi.UpdateAuthorizationState.CONSTRUCTOR) {
                val authState = (tdEvent as TdApi.UpdateAuthorizationState).authorizationState
                    if(authState.constructor == TdApi.AuthorizationStateReady.CONSTRUCTOR) {
                        return@filter true
                    }

            }
            false

        }.blockingFirst()

        println(authStateReady)

    }


}