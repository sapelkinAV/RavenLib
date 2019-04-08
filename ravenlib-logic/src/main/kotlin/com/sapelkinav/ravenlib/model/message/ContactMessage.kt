package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ContactMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageContact
    get() = super.content as TdApi.MessageContact
}