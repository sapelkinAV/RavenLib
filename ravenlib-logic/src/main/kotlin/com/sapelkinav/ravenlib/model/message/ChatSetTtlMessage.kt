package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatSetTtlMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatSetTtl
    get() = super.content as TdApi.MessageChatSetTtl
}