package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class AnimationMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageAnimation
    get() = super.content as TdApi.MessageAnimation
}