package com.sapelkinav.ravenlib

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.client.TdlibParameters
import com.sapelkinav.ravenlib.exception.TelegramException
import com.sapelkinav.ravenlib.handlers.AuthorizationHandler
import com.sapelkinav.ravenlib.handlers.UpdatesHandler
import com.sapelkinav.ravenlib.model.chat.ChatRepository
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import org.scijava.nativelib.NativeLoader
import java.io.IOException


class RavenLib (val tdlibParameters: TdlibParameters,
                val phone:String,
                val getCode:() -> String ,
                val getPassword:() -> String
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
    private val defaultErrorHandler = Client.ExceptionHandler { error->
        errorEvents.onNext(error)
    }

    val tdEvents = PublishSubject.create<TdApi.Object>()
    val authorizationEvents: Subject<TdApi.Object> = BehaviorSubject.create<TdApi.Object>()
    val errorEvents: Subject<Throwable> = PublishSubject.create()
    val updatesHandler = UpdatesHandler(tdEvents, authorizationEvents, errorEvents)

    val client = Client.create(
        updatesHandler,
        defaultErrorHandler,
        defaultErrorHandler
    )
    val authorizationHandler = AuthorizationHandler(
        client,
        tdlibParameters,
        authorizationEvents,
        phone,
        getCode,
        getPassword
    )

    val ravenClient = RavenClient(client)
    val chatRepository = ChatRepository(ravenClient)

    override fun close() {
        ravenClient.tdCall(TdApi.Close()) {}
    }



}