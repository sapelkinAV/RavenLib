package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatDeleteMemberMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatDeleteMember
    get() = super.content as TdApi.MessageChatDeleteMember
}