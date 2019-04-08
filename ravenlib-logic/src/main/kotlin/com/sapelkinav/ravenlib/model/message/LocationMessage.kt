package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class LocationMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageLocation
    get() = super.content as TdApi.MessageLocation
}