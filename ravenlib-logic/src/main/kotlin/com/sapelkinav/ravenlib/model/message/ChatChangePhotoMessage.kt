package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ChatChangePhotoMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageChatChangePhoto
    get() = super.content as TdApi.MessageChatChangePhoto
}