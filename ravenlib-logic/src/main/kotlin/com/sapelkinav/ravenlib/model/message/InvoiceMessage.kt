package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi

class InvoiceMessage(message: Message): Message(message){
    constructor(message: TdApi.Message) : this(Message(message))

    val content: TdApi.MessageInvoice
    get() = super.content as TdApi.MessageInvoice
}