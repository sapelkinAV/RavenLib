package com.sapelkinav.ravenlib.model.user

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.exception.TelegramException
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Supergroup

class Supergroup(val ravenClient: RavenClient) : Supergroup(){
    constructor(
        ravenClient: RavenClient,
        supergroup: TdApi.Supergroup
    ) : this(ravenClient) {
        this.id = supergroup.id
        this.username = supergroup.username
        this.date = supergroup.date
        this.status = supergroup.status
        this.memberCount = supergroup.memberCount
        this.anyoneCanInvite = supergroup.anyoneCanInvite
        this.signMessages = supergroup.signMessages
        this.isChannel = supergroup.isChannel
        this.isVerified = supergroup.isVerified
        this.restrictionReason = supergroup.restrictionReason
    }

    fun joinChat() {
        ravenClient.tdCall(TdApi.JoinChatByInviteLink("t.me/$username")) { tdEvent ->
            when(tdEvent.constructor) {
                TdApi.Error.CONSTRUCTOR -> {
                    val error = tdEvent as TdApi.Error
                    throw TelegramException(error.code, error.message)
                }
                else -> ""
            }

        }
    }


}