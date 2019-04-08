package com.sapelkinav.ravenlib

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.client.TdlibParameters
import com.sapelkinav.ravenlib.handlers.AuthorizationHandler
import com.sapelkinav.ravenlib.handlers.UpdatesHandler
import com.sapelkinav.ravenlib.model.chat.ChatRepository
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
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

    val tdEvents = PublishSubject.create<TdApi.Object>()
    val authorizationEvents = BehaviorSubject.create<TdApi.Object>()
    val updatesHandler = UpdatesHandler(tdEvents,authorizationEvents)

    val client = Client.create(
        updatesHandler,
        Client.ExceptionHandler { println(it) },
        Client.ExceptionHandler { println(it) }
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