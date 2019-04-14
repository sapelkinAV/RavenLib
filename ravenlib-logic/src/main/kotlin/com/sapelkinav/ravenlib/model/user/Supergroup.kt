package com.sapelkinav.ravenlib.model.user

import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Supergroup

class Supergroup() : Supergroup(){
    constructor(
        supergroup: TdApi.Supergroup
    ) : this() {
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


}