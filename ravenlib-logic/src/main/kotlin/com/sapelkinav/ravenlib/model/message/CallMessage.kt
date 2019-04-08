package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class CallMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageCall
    get() = super.content as TdApi.MessageCall
}