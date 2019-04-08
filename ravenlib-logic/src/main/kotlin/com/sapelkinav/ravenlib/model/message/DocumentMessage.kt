package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class DocumentMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageDocument
    get() = super.content as TdApi.MessageDocument
}