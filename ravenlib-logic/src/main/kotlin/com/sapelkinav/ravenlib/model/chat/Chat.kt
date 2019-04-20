package com.sapelkinav.ravenlib.model.chat

import com.sapelkinav.ravenlib.client.RavenClient
import com.sapelkinav.ravenlib.exception.TelegramException
import com.sapelkinav.ravenlib.model.message.Message
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import org.drinkless.tdlib.TdApi


open class Chat(
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
        tdapiChat.isMarkedAsUnread,
        tdapiChat.isSponsored,
        tdapiChat.canBeReported,
        tdapiChat.defaultDisableNotification,
        tdapiChat.unreadCount,
        tdapiChat.lastReadInboxMessageId,
        tdapiChat.lastReadOutboxMessageId,
        tdapiChat.unreadMentionCount,
        tdapiChat.notificationSettings,
        tdapiChat.replyMarkupMessageId,
        tdapiChat.draftMessage,
        tdapiChat.clientData
    ) {

    fun getMessages(
        limit: Long = Long.MAX_VALUE,
        countOfMessagesPerRequest: Int = 20,
        query: String = "",
        // tdlib search filter
        messageTypeFilter: TdApi.SearchMessagesFilter? = null,
        // message filter
        filter: (Message) -> Boolean = { true },
        // condition of instant search cancellation
        cancellationFilter: (Message) -> Boolean = { true },
        senderId: Int = 0
    ): Flowable<Message> {

        var messageCount = 0L
        var fromMessageId = 0L
        var isSearchMustBeCanceled = false

        return Flowable.create({ emitter ->
            do {
                val tdMessages = ravenClient.tdCall(TdApi.SearchChatMessages(
                    id,
                    query,
                    senderId,
                    fromMessageId,
                    0,
                    countOfMessagesPerRequest,
                    messageTypeFilter
                ), { tdObject ->
                    if (tdObject.constructor == TdApi.Error.CONSTRUCTOR) {
                        val error = tdObject as TdApi.Error
                        emitter.onError(TelegramException(error.code, error.message))
                    }
                    return@tdCall (tdObject as TdApi.Messages).messages.toList()
                }) { error ->
                    emitter.onError(error)
                }

                val messagesToEmmit = if (messageCount + tdMessages.size > limit) {
                    val allowedElementsCount = limit - messageCount
                    tdMessages.subList(0, allowedElementsCount.toInt())
                } else {
                    tdMessages
                }.map(this::toRavenMessage)

                fromMessageId = if (tdMessages.isNotEmpty()) tdMessages.last().id else Long.MAX_VALUE
                messageCount += tdMessages.size

                messagesToEmmit
                    .filter(filter)
                    .forEach { message ->
                        if (!cancellationFilter(message)) {
                            isSearchMustBeCanceled = true
                            return@forEach
                        }
                        emitter.onNext(message)
                    }

            } while (messageCount < limit && tdMessages.isNotEmpty() && !isSearchMustBeCanceled)

            emitter.onComplete()

        }, BackpressureStrategy.BUFFER)

    }

    private fun toRavenMessage(message: TdApi.Message): Message =
        when (message.content.constructor) {
            TdApi.MessageText.CONSTRUCTOR -> Message.MessageText(message)
            TdApi.MessageAnimation.CONSTRUCTOR -> Message.MessageAnimation(message)
            TdApi.MessageAudio.CONSTRUCTOR -> Message.MessageAudio(message)
            TdApi.MessageDocument.CONSTRUCTOR -> Message.MessageDocument(message)
            TdApi.MessagePhoto.CONSTRUCTOR -> Message.MessagePhoto(message)
            TdApi.MessageExpiredPhoto.CONSTRUCTOR -> Message.MessageExpiredPhoto(message)
            TdApi.MessageSticker.CONSTRUCTOR -> Message.MessageSticker(message)
            TdApi.MessageVideo.CONSTRUCTOR -> Message.MessageVideo(message)
            TdApi.MessageExpiredVideo.CONSTRUCTOR -> Message.MessageExpiredVideo(message)
            TdApi.MessageVideoNote.CONSTRUCTOR -> Message.MessageVideoNote(message)
            TdApi.MessageVoiceNote.CONSTRUCTOR -> Message.MessageVoiceNote(message)
            TdApi.MessageLocation.CONSTRUCTOR -> Message.MessageLocation(message)
            TdApi.MessageVenue.CONSTRUCTOR -> Message.MessageVenue(message)
            TdApi.MessageContact.CONSTRUCTOR -> Message.MessageContact(message)
            TdApi.MessageGame.CONSTRUCTOR -> Message.MessageGame(message)
            TdApi.MessageInvoice.CONSTRUCTOR -> Message.MessageInvoice(message)
            TdApi.MessageCall.CONSTRUCTOR -> Message.MessageCall(message)
            TdApi.MessageBasicGroupChatCreate.CONSTRUCTOR -> Message.MessageBasicGroupChatCreate(message)
            TdApi.MessageSupergroupChatCreate.CONSTRUCTOR -> Message.MessageSupergroupChatCreate(message)
            TdApi.MessageChatChangeTitle.CONSTRUCTOR -> Message.MessageChatChangeTitle(message)
            TdApi.MessageChatChangePhoto.CONSTRUCTOR -> Message.MessageChatChangePhoto(message)
            TdApi.MessageChatDeletePhoto.CONSTRUCTOR -> Message.MessageChatDeletePhoto(message)
            TdApi.MessageChatAddMembers.CONSTRUCTOR -> Message.MessageChatAddMembers(message)
            TdApi.MessageChatJoinByLink.CONSTRUCTOR -> Message.MessageChatJoinByLink(message)
            TdApi.MessageChatDeleteMember.CONSTRUCTOR -> Message.MessageChatDeleteMember(message)
            TdApi.MessageChatUpgradeTo.CONSTRUCTOR -> Message.MessageChatUpgradeTo(message)
            TdApi.MessageChatUpgradeFrom.CONSTRUCTOR -> Message.MessageChatUpgradeFrom(message)
            TdApi.MessagePinMessage.CONSTRUCTOR -> Message.MessagePinMessage(message)
            TdApi.MessageScreenshotTaken.CONSTRUCTOR -> Message.MessageScreenshotTaken(message)
            TdApi.MessageChatSetTtl.CONSTRUCTOR -> Message.MessageChatSetTtl(message)
            TdApi.MessageCustomServiceAction.CONSTRUCTOR -> Message.MessageCustomServiceAction(message)
            TdApi.MessageGameScore.CONSTRUCTOR -> Message.MessageGameScore(message)
            TdApi.MessagePaymentSuccessful.CONSTRUCTOR -> Message.MessagePaymentSuccessful(message)
            TdApi.MessagePaymentSuccessfulBot.CONSTRUCTOR -> Message.MessagePaymentSuccessfulBot(message)
            TdApi.MessageContactRegistered.CONSTRUCTOR -> Message.MessageContactRegistered(message)
            TdApi.MessageWebsiteConnected.CONSTRUCTOR -> Message.MessageWebsiteConnected(message)
            TdApi.MessagePassportDataSent.CONSTRUCTOR -> Message.MessagePassportDataSent(message)
            TdApi.MessagePassportDataReceived.CONSTRUCTOR -> Message.MessagePassportDataReceived(message)
            TdApi.MessageUnsupported.CONSTRUCTOR -> Message.MessageUnsupported(message)
            else -> Message.MessageUnsupported(message)
        }

}