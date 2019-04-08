package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatUpgradeToMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatUpgradeTo
    get() = super.content as TdApi.MessageChatUpgradeTo
}