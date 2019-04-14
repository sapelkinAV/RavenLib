package com.sapelkinav.ravenlib.model.chat

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.exception.TelegramException
import com.sapelkinav.ravenlib.model.user.Supergroup
import org.drinkless.tdlib.TdApi

class SupergroupChat(
    tdapiChat: TdApi.Chat,
    private val ravenClient: RavenClient
    //Amount of chats that will return per getMessages call. 0 - 100 .
) : Chat(tdapiChat, ravenClient) {

    private val supergroupType = type as TdApi.ChatTypeSupergroup

    val supergroup = ravenClient.tdCall(TdApi.GetSupergroup(supergroupType.supergroupId)) { tdEvent ->
        when (tdEvent.constructor) {
            TdApi.Supergroup.CONSTRUCTOR -> return@tdCall Supergroup(ravenClient, tdEvent as TdApi.Supergroup)
            TdApi.Error.CONSTRUCTOR -> {
                val error = tdEvent as TdApi.Error
                throw TelegramException(error.code, error.message)
            }
            else -> Supergroup(ravenClient)
        }
    }

    fun leaveChat() {
        ravenClient.tdCall(TdApi.LeaveChat(id)) {tdEvent ->
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