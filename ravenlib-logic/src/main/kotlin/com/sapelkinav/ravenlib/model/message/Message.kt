package com.sapelkinav.ravenlib.model.message

import org.drinkless.tdlib.TdApi


open class Message(tdApiMessage:TdApi.Message)
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

)


