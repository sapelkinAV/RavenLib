package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class GameMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageGame
    get() = super.content as TdApi.MessageGame
}