package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class TextMessage(message: Message) : Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content:TdApi.MessageText
    get() = super.content as TdApi.MessageText
}