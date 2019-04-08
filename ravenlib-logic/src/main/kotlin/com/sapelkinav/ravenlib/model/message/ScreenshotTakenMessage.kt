package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class ScreenshotTakenMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageScreenshotTaken
    get() = super.content as TdApi.MessageScreenshotTaken
}