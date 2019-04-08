package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ExpiredPhotoMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageExpiredPhoto
    get() = super.content as TdApi.MessageExpiredPhoto
}