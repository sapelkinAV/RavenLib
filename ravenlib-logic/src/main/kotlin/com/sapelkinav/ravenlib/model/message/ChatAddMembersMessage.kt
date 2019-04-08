package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatAddMembersMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatAddMembers
    get() = super.content as TdApi.MessageChatAddMembers
}