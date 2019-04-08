package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatDeletePhotoMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatDeletePhoto
    get() = super.content as TdApi.MessageChatDeletePhoto
}