package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ContactRegisteredMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageContactRegistered
    get() = super.content as TdApi.MessageContactRegistered
}