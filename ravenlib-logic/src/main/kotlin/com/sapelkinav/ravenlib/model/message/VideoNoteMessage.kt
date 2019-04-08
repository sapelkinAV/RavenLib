package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class VideoNoteMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageVideoNote
    get() = super.content as TdApi.MessageVideoNote
}