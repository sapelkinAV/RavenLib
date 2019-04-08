package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ExpiredVideoMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageExpiredVideo
    get() = super.content as TdApi.MessageExpiredVideo
}