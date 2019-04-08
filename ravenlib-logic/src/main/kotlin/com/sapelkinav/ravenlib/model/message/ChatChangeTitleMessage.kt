package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatChangeTitleMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatChangeTitle
    get() = super.content as TdApi.MessageChatChangeTitle
}