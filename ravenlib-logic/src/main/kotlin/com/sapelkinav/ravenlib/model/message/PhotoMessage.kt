package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class PhotoMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessagePhoto
    get() = super.content as TdApi.MessagePhoto
}