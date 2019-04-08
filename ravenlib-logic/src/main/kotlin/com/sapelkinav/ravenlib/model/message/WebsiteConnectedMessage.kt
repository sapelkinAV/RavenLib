package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class WebsiteConnectedMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageWebsiteConnected
    get() = super.content as TdApi.MessageWebsiteConnected
}