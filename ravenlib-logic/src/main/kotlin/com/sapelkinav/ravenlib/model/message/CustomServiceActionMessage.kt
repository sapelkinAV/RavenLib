package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class CustomServiceActionMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageCustomServiceAction
    get() = super.content as TdApi.MessageCustomServiceAction
}