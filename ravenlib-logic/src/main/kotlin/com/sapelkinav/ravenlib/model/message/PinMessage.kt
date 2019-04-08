package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class PinMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessagePinMessage
    get() = super.content as TdApi.MessagePinMessage
}