package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class BasicGroupChatCreateMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageBasicGroupChatCreate
    get() = super.content as TdApi.MessageBasicGroupChatCreate
}