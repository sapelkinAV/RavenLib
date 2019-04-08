package com.sapelkinav.ravenlib.exception

class TelegramException(val code:Int,
                        errorMessage:String)
    : RuntimeException(errorMessage)