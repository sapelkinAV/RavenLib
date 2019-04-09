package com.sapelkinav.ravenlib.exception

class AuthorisationException(code: Int, errorMessage: String)
    : TelegramException(code, errorMessage)