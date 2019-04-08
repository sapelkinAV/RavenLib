package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class GameScoreMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageGameScore
    get() = super.content as TdApi.MessageGameScore
}