package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class PaymentSuccessfulMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessagePaymentSuccessful
    get() = super.content as TdApi.MessagePaymentSuccessful
}