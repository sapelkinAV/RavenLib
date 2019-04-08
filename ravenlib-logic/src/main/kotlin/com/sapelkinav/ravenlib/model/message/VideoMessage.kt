package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class VideoMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageVideo
    get() = super.content as TdApi.MessageVideo
}