package com.sapelkinav.ravenlib.client
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi

import com.sapelkinav.ravenlib.utils.tdCall


class RavenClient(val client: Client) {

    fun <T> tdCall(query:TdApi.Function, handler:(TdApi.Object)->T) :T {
        return client.tdCall(query,handler)
    }

    fun <T> tdCall(query:TdApi.Function, handler:(TdApi.Object)->T, exceptionHandler:(Throwable)->Unit= {
        it.printStackTrace()
    }) :T {
        return client.tdCall(query,handler,exceptionHandler)
    }

}