package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatJoinByLinkMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatJoinByLink
    get() = super.content as TdApi.MessageChatJoinByLink
}