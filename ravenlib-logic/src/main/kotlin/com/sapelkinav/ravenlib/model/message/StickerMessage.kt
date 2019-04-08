package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class StickerMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageSticker
    get() = super.content as TdApi.MessageSticker
}