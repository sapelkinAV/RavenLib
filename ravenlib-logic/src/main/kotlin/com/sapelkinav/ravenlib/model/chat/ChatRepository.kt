package com.sapelkinav.ravenlib.model.chat

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.exception.TelegramException
import org.drinkless.tdlib.TdApi
import java.util.stream.Collectors
import kotlin.streams.toList

class ChatRepository(private val ravenClient: RavenClient) {


    fun getChatList(limit: Int = 200): List<Chat> {
        var offsetOrder = Long.MAX_VALUE
        var offsetChatId = 0L
        val chatList = ArrayList<TdApi.Chat>()

        do {
            val chatIds = ravenClient.tdCall(TdApi.GetChats(offsetOrder, offsetChatId, limit)) {
                (it as TdApi.Chats).chatIds.toList()
            }
            chatList.addAll(
                chatIds
                    .stream()
                    .map { chatId ->
                        ravenClient.tdCall(TdApi.GetChat(chatId), {
                            it as TdApi.Chat
                        }) {
                            it.printStackTrace()
                        }
                    }.collect(Collectors.toList())
            )
            if (chatList.isNotEmpty()) {
                val last = chatList.last()
                offsetChatId = last.id
                offsetOrder = last.order
            }
        } while (chatIds.isNotEmpty())

        return chatList.map { Chat(it, ravenClient) }

    }


    fun getBasicGroupChats(limit: Int = 200): List<Chat> {
        return getChatList(limit)
            .stream()
            .filter { chat ->
                chat.type is TdApi.ChatTypeBasicGroup
            }.toList()
    }

    fun getSuperGroupChats(limit: Int = 200): List<Chat> {
        return getChatList(limit).stream().filter { chat ->
            chat.type is TdApi.ChatTypeSupergroup
        }.toList()
    }

    fun getPrivateChats(limit: Int = 200): List<Chat> {
        return getChatList(limit)
            .stream().filter { chat ->
                chat.type is TdApi.ChatTypePrivate
            }.toList()
    }

    fun getSecretChats(limit: Int = 200): List<Chat> {
        return getChatList(limit).stream().filter { chat -> chat.type is TdApi.ChatTypeSecret }.toList()
    }

    fun searchPublicChat(supergroupTitle: String): Chat {

        return ravenClient.tdCall(TdApi.SearchPublicChat(supergroupTitle)) {
            if (it.constructor == TdApi.Error.CONSTRUCTOR) {
                val error = it as TdApi.Error
                throw TelegramException(error.code, error.message)
            }
            return@tdCall Chat(it as TdApi.Chat, ravenClient)
        }

    }


}