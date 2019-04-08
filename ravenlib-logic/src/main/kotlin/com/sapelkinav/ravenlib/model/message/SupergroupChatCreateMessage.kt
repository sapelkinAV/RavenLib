package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class SupergroupChatCreateMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageSupergroupChatCreate
    get() = super.content as TdApi.MessageSupergroupChatCreate
}