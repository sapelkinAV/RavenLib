package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class UnsupportedMessage (message:Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content:TdApi.MessageUnsupported
    get() = super.content as TdApi.MessageUnsupported
}