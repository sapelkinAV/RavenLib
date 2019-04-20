package com.sapelkinav.ravenlib.utils

import io.reactivex.subjects.BehaviorSubject
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi


fun <T> Client.tdCall(query: TdApi.Function, handler: (TdApi.Object) -> T): T {
    return tdCall(query, handler) { exception ->
        exception.printStackTrace()
    }
}

fun <T> Client.tdCall(query: TdApi.Function, handler: (TdApi.Object) -> T, exceptionHandler: (Throwable) -> Unit): T {
    val resultObservable: BehaviorSubject<T> = BehaviorSubject.create()

    send(query, { tdEvent ->
        val result = handler(tdEvent)
        resultObservable.onNext(result)
        resultObservable.onComplete()
    }, { exception ->
        exceptionHandler(exception)
    })

    return resultObservable.blockingFirst()
}

