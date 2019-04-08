package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class VoiceNoteMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageVoiceNote
    get() = super.content as TdApi.MessageVoiceNote
}