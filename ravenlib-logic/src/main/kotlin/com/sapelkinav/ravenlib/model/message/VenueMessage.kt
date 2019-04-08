package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class VenueMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageVenue
    get() = super.content as TdApi.MessageVenue
}