package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class AudioMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageAudio
    get() = super.content as TdApi.MessageAudio
}