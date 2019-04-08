package com.sapelkinav.ravenlib.model.chat

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.exception.TelegramException
import com.sapelkinav.ravenlib.model.message.*
import org.drinkless.tdlib.TdApi


class Chat(
    tdapiChat: TdApi.Chat,
    private val ravenClient: RavenClient
    //Amount of chats that will return per getMessages call. 0 - 100 .
) :
    TdApi.Chat(
        tdapiChat.id,
        tdapiChat.type,
        tdapiChat.title,
        tdapiChat.photo,
        tdapiChat.lastMessage,
        tdapiChat.order,
        tdapiChat.isPinned,
        tdapiChat.canBeReported,
        tdapiChat.unreadCount,
        tdapiChat.lastReadInboxMessageId,
        tdapiChat.lastReadOutboxMessageId,
        tdapiChat.unreadMentionCount,
        tdapiChat.notificationSettings,
        tdapiChat.replyMarkupMessageId,
        tdapiChat.draftMessage,
        tdapiChat.clientData
    ) {

    private val LIMIT: Int = 20
    private val AMOUNT_OF_CHATS_PER_REQUEST: Int = 10

    fun getMessages(
        limit: Int = LIMIT,
        filter: TdApi.SearchMessagesFilter? = null,
        query: String = "",
        senderId: Int = 0,
        startingFromMessageId: Long = 0L
    ): List<TdApi.Message> {
        val resultMessages = ArrayList<TdApi.Message>()
        var fromMessageId = startingFromMessageId
        do {
            val tdMessages = ravenClient.tdCall(TdApi.SearchChatMessages(
                id,
                query,
                senderId,
                fromMessageId,
                0,
                AMOUNT_OF_CHATS_PER_REQUEST,
                filter
            ),
                { tdObject ->
                    if (tdObject.constructor == TdApi.Error.CONSTRUCTOR) {
                        val error = tdObject as TdApi.Error
                        throw TelegramException(error.code, error.message)
                    }
                    return@tdCall (tdObject as TdApi.Messages).messages.toList()
                }

            ) { it.printStackTrace() }

            if (resultMessages.size + tdMessages.size > limit) {
                val allowedElementsCount = limit - resultMessages.size
                resultMessages.addAll(
                    tdMessages.subList(0, allowedElementsCount)
                )
            } else {
                resultMessages.addAll(tdMessages)
            }

            fromMessageId = tdMessages.last().id
        } while (tdMessages.isNotEmpty() && resultMessages.size < limit)

        return resultMessages
    }

    fun getAudioMessages(
        limit: Int = 20,
        query: String = "",
        senderId: Int = 0
    ): List<AudioMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterAudio(), query, senderId).map { AudioMessage(it) }
    }

    fun getPhotoMessages(
        limit: Int = 20,
        query: String = "",
        senderId: Int = 0
    ): List<TdApi.MessagePhoto> {
        return getMessages(
            limit,
            TdApi.SearchMessagesFilterPhoto(),
            query,
            senderId
        ).map { it.content as TdApi.MessagePhoto }
    }

    fun getAnimationMessages(limit: Int = 20, query: String = "", senderId: Int): List<AnimationMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterAnimation(), query, senderId).map { AnimationMessage(it) }
    }

    fun getDocumentMessages(limit: Int = 20, query: String = "", senderId: Int): List<DocumentMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterDocument(), query, senderId).map { DocumentMessage(it) }
    }

    fun getVideoMessages(limit: Int = 20, query: String = "", senderId: Int): List<VideoMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterVideo(), query, senderId).map {
            VideoMessage(it)
        }
    }

    fun getVoiceNoteMessages(limit: Int = 20, query: String = "", senderId: Int): List<VoiceNoteMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterVoiceNote(), query, senderId).map { VoiceNoteMessage(it) }
    }

    fun getPhotoAndVideoMessages(limit: Int = 20, query: String = "", senderId: Int): List<Message> {
        return getMessages(limit, TdApi.SearchMessagesFilterPhotoAndVideo(), query, senderId).map { Message(it) }
    }

    fun getUrlMessages(limit: Int = 20, query: String = "", senderId: Int): List<TextMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterUrl(), query, senderId).map { TextMessage(it) }
    }

    fun getChatPhotoMessages(limit: Int = 20, query: String = "", senderId: Int): List<PhotoMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterChatPhoto(), query, senderId).map { PhotoMessage(it) }
    }

    fun getCallMessages(limit: Int = 20, query: String = "", senderId: Int): List<CallMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterCall(), query, senderId).map { CallMessage(it) }
    }

    fun getMissedCallMessages(limit: Int = 20, query: String = "", senderId: Int): List<CallMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterMissedCall(), query, senderId).map { CallMessage(it) }
    }

    fun getVideoNoteMessages(limit: Int = 20, query: String = "", senderId: Int): List<VideoNoteMessage> {
        return getMessages(limit, TdApi.SearchMessagesFilterVideoNote(), query, senderId).map { VideoNoteMessage(it) }
    }

    fun getVoiceAndVideoNoteMessages(limit: Int = 20, query: String = "", senderId: Int): List<Message> {
        return getMessages(limit, TdApi.SearchMessagesFilterVoiceAndVideoNote(), query, senderId).map { Message(it) }
    }

    fun getMentionMessages(limit: Int = 20, query: String = "", senderId: Int): List<Message> {
        return getMessages(limit, TdApi.SearchMessagesFilterMention(), query, senderId).map { Message(it) }
    }

    fun getUnreadMentionMessages(limit: Int = 20, query: String = "", senderId: Int): List<Message> {
        return getMessages(limit, TdApi.SearchMessagesFilterUnreadMention(), query, senderId).map { Message(it) }
    }

    fun getMessagesWithoutFilter(limit: Int = 20, query: String = "", senderId: Int = 0): List<Message> {
        return getMessages(limit, TdApi.SearchMessagesFilterEmpty(), query, senderId).map { Message(it) }
    }

}