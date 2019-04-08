package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class PaymentSuccessfulBotMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessagePaymentSuccessfulBot
    get() = super.content as TdApi.MessagePaymentSuccessfulBot
}