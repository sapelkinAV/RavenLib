package com.sapelkinav.ravenlib.exception

open class TelegramException(val code:Int,
                        errorMessage:String)
    : RuntimeException(errorMessage)