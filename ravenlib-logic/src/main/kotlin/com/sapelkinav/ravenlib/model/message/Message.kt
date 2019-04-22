package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi


public sealed class Message(tdApiMessage:TdApi.Message)
    :TdApi.Message(
        /**
         * Unique message identifier.
         */
        tdApiMessage.id,
        /**
         * Identifier of the user who sent the message; 0 if unknown. It is unknown for channel posts.
         */
        tdApiMessage.senderUserId,
        /**
         * Chat identifier.
         */
        tdApiMessage.chatId,
        /**
         * Information about the sending state of the message; may be null.
         */
        tdApiMessage.sendingState,
        /**
         * True, if the message is outgoing.
         */
        tdApiMessage.isOutgoing,
        /**
         * True, if the message can be edited.
         */
        tdApiMessage.canBeEdited,
        /**
         * True, if the message can be forwarded.
         */
        tdApiMessage.canBeForwarded,
        /**
         * True, if the message can be deleted only for the current user while other users will continue to see it.
         */
        tdApiMessage.canBeDeletedOnlyForSelf,
        /**
         * True, if the message can be deleted for all users.
         */
        tdApiMessage.canBeDeletedForAllUsers,
        /**
         * True, if the message is a channel post. All messages to channels are channel posts, all other messages are not channel posts.
         */
        tdApiMessage.isChannelPost,
        /**
         * True, if the message contains an unread mention for the current user.
         */
        tdApiMessage.containsUnreadMention,
        /**
         * Point in time (Unix timestamp) when the message was sent.
         */
        tdApiMessage.date,
        /**
         * Point in time (Unix timestamp) when the message was last edited.
         */
        tdApiMessage.editDate,
        /**
         * Information about the initial message sender; may be null.
         */
        tdApiMessage.forwardInfo,
        /**
         * If non-zero, the identifier of the message this message is replying to; can be the identifier of a deleted message.
         */
        tdApiMessage.replyToMessageId,
        /**
         * For self-destructing messages, the message's TTL (Time To Live), in seconds; 0 if none. TDLib will send updateDeleteMessages or updateMessageContent once the TTL expires.
         */
        tdApiMessage.ttl,
        /**
         * Time left before the message expires, in seconds.
         */
        tdApiMessage.ttlExpiresIn,
        /**
         * If non-zero, the user identifier of the bot through which this message was sent.
         */
        tdApiMessage.viaBotUserId,
        /**
         * For channel posts, optional author signature.
         */
        tdApiMessage.authorSignature,
        /**
         * Number of times this message was viewed.
         */
        tdApiMessage.views,
        /**
         * Unique identifier of an album this message belongs to. Only photos and videos can be grouped together in albums.
         */
        tdApiMessage.mediaAlbumId,
        /**
         * Content of the message.
         */
        tdApiMessage.content,

        /**
         * Reply markup for the message; may be null.
         */
        tdApiMessage.replyMarkup
) {


    public class MessageText(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
        get() = this.content as TdApi.MessageText

    }
    public class MessageAnimation(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageAnimation
    }
    public class MessageAudio(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageAudio
    }
    public class MessageDocument(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageDocument
    }
    public class MessagePhoto(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessagePhoto
    }
    public class MessageExpiredPhoto(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageExpiredPhoto
    }
    public class MessageSticker(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageSticker
    }
    public class MessageVideo(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageVideo
    }
    public class MessageExpiredVideo(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageExpiredVideo
    }
    public class MessageVideoNote(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageVideoNote
    }
    public class MessageVoiceNote(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageVoiceNote
    }
    public class MessageLocation(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageLocation
    }
    public class MessageVenue(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageVenue
    }
    public class MessageContact(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageContact
    }
    public class MessageGame(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageGame
    }
    public class MessageInvoice(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageInvoice
    }
    public class MessageCall(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageCall
    }
    public class MessageBasicGroupChatCreate(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageBasicGroupChatCreate
    }
    public class MessageSupergroupChatCreate(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageSupergroupChatCreate
    }
    public class MessageChatChangeTitle(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatChangeTitle
    }
    public class MessageChatChangePhoto(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatChangePhoto
    }
    public class MessageChatDeletePhoto(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatDeletePhoto
    }
    public class MessageChatAddMembers(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatAddMembers
    }
    public class MessageChatJoinByLink(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatJoinByLink
    }
    public class MessageChatDeleteMember(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatDeleteMember
    }
    public class MessageChatUpgradeTo(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatUpgradeTo
    }
    public class MessageChatUpgradeFrom(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatUpgradeFrom
    }
    public class MessagePinMessage(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessagePinMessage
    }
    public class MessageScreenshotTaken(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageScreenshotTaken
    }
    public class MessageChatSetTtl(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageChatSetTtl
    }
    public class MessageCustomServiceAction(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageCustomServiceAction
    }
    public class MessageGameScore(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageGameScore
    }
    public class MessagePaymentSuccessful(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessagePaymentSuccessful
    }
    public class MessagePaymentSuccessfulBot(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessagePaymentSuccessfulBot
    }
    public class MessageContactRegistered(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageContactRegistered
    }
    public class MessageWebsiteConnected(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageWebsiteConnected
    }
    public class MessagePassportDataSent(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessagePassportDataSent
    }
    public class MessagePassportDataReceived(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessagePassportDataReceived
    }
    public class MessageUnsupported(tdApiMessage:TdApi.Message) : Message(tdApiMessage) {
        val messageContent
            get() = this.content as TdApi.MessageUnsupported
    }

}


