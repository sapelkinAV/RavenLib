package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatUpgradeFromMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatUpgradeFrom
    get() = super.content as TdApi.MessageChatUpgradeFrom
}