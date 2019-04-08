package com.sapelkinav.ravenlib.handlers

import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi


class UpdatesHandler(val tdEvents : PublishSubject<TdApi.Object>,
                     val authorizationEvents: BehaviorSubject<TdApi.Object>) : Client.ResultHandler {




    override fun onResult(tdEvent: TdApi.Object?){
        if (tdEvent != null) {

            when (tdEvent.constructor){
                TdApi.UpdateAuthorizationState.CONSTRUCTOR -> authorizationEvents.onNext(tdEvent)
                else -> tdEvents.onNext(tdEvent)
            }

        }
//        if (tdEvent != null) {
//            when(tdEvent.constructor) {
//
//                //Authorization updates
//                TdApi.UpdateAuthorizationState.CONSTRUCTOR -> authorizationHandler.handleAuthorization((tdEvent as TdApi.UpdateAuthorizationState).authorizationState)
//
//                //User updates
//                TdApi.UpdateUser.CONSTRUCTOR ->{
//                    val updateUser = tdEvent as TdApi.UpdateUser
//                    users[updateUser.user.id] = updateUser.user
//                }
//
//                //User status updates
//                TdApi.UpdateUserStatus.CONSTRUCTOR ->{
//                    val updateUserStatus = tdEvent as TdApi.UpdateUserStatus
//                    val user = users[updateUserStatus.userId]
//                    if (user != null) {
//                        synchronized(user){
//                            users[updateUserStatus.userId]?.status = updateUserStatus.status
//                        }
//                    }
//
//                }
//
//                //Group chat update
//                TdApi.UpdateBasicGroup.CONSTRUCTOR ->{
//                    val basicGroupUpdate = tdEvent as TdApi.UpdateBasicGroup
//                    basicGroups[basicGroupUpdate.basicGroup.id] = basicGroupUpdate.basicGroup
//                }
//
//                //Supergroup update
//                TdApi.UpdateSupergroup.CONSTRUCTOR ->{
//                    val updateSupergoup = tdEvent as TdApi.UpdateSupergroup
//                    superGroups[updateSupergoup.supergroup.id] = updateSupergoup.supergroup
//                }
//
//                //Secret chat update
//                TdApi.UpdateSecretChat.CONSTRUCTOR ->{
//                    val updateSecretChat = tdEvent as TdApi.UpdateSecretChat
//                    secretChats[updateSecretChat.secretChat.id] = updateSecretChat.secretChat
//                }
//
//                // New chat update
//                TdApi.UpdateNewChat.CONSTRUCTOR->{
//                    val updateNewChat = tdEvent as TdApi.UpdateNewChat
//                    val chat = updateNewChat.chat
//                    synchronized(chat) {
//                        chats[chat.id] = chat
//                        val order = chat.order
//                        chat.order = 0
//                        setChatOrder(chat,order)
//                    }
//                }
//
//                //Update chat title
//                TdApi.UpdateChatTitle.CONSTRUCTOR ->{
//                    val updateChat = tdEvent as TdApi.UpdateChatTitle
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.title = updateChat.title
//                        }
//                    }
//                }
//
//                //Update chat photo
//                TdApi.UpdateChatPhoto.CONSTRUCTOR->{
//                    val updateChat = tdEvent as TdApi.UpdateChatPhoto
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.photo = updateChat.photo
//                        }
//                    }
//                }
//
//                //Update last message
//                TdApi.UpdateChatLastMessage.CONSTRUCTOR->{
//                    val updateChat = tdEvent as TdApi.UpdateChatLastMessage
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.lastMessage = updateChat.lastMessage
//                            setChatOrder(chat, updateChat.order)
//                        }
//                    }
//                }
//
//                //Update chat order
//                TdApi.UpdateChatOrder.CONSTRUCTOR->{
//                    val updateChat = tdEvent as TdApi.UpdateChatOrder
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            setChatOrder(chat, updateChat.order)
//                        }
//                    }
//                }
//
//                //Update pinned chat
//                TdApi.UpdateChatIsPinned.CONSTRUCTOR->{
//                    val updateChat = tdEvent as TdApi.UpdateChatIsPinned
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.isPinned = updateChat.isPinned
//                            setChatOrder(chat, updateChat.order)
//                        }
//                    }
//                }
//
//                //Update read inbox
//                TdApi.UpdateChatReadInbox.CONSTRUCTOR->{
//                    val updateChat = tdEvent as TdApi.UpdateChatReadInbox
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.lastReadInboxMessageId = updateChat.lastReadInboxMessageId
//                            chat.unreadCount = updateChat.unreadCount
//                        }
//                    }
//                }
//
//                //Update outbox
//                TdApi.UpdateChatReadOutbox.CONSTRUCTOR ->{
//                    val updateChat = tdEvent as TdApi.UpdateChatReadOutbox
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.lastReadOutboxMessageId = updateChat.lastReadOutboxMessageId
//                        }
//                    }
//                }
//
//                //Update mention count
//                TdApi.UpdateChatUnreadMentionCount.CONSTRUCTOR -> {
//                    val updateChat = tdEvent as TdApi.UpdateChatUnreadMentionCount
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.unreadMentionCount = updateChat.unreadMentionCount
//                        }
//                    }
//                }
//
//                //Update message mention read
//                TdApi.UpdateMessageMentionRead.CONSTRUCTOR ->{
//                    val updateChat = tdEvent as TdApi.UpdateMessageMentionRead
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.unreadMentionCount = updateChat.unreadMentionCount
//                        }
//                    }
//                }
//
//                //Update chat reply markup
//                TdApi.UpdateChatReplyMarkup.CONSTRUCTOR -> {
//                    val updateChat = tdEvent as TdApi.UpdateChatReplyMarkup
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.replyMarkupMessageId = updateChat.replyMarkupMessageId
//                        }
//                    }
//                }
//
//                //Update chat draft message
//                TdApi.UpdateChatDraftMessage.CONSTRUCTOR ->{
//                    val updateChat = tdEvent as TdApi.UpdateChatDraftMessage
//                    val chat = chats[updateChat.chatId]
//                    if (chat != null) {
//                        synchronized(chat) {
//                            chat.draftMessage = updateChat.draftMessage
//                            setChatOrder(chat, updateChat.order)
//                        }
//                    }
//                }
//
//                //Update notification settings
//                TdApi.UpdateNotificationSettings.CONSTRUCTOR ->{
//                    val update = tdEvent as TdApi.UpdateNotificationSettings
//                    if (update.scope is TdApi.NotificationSettingsScopeChat) {
//                        val chat = chats[(update.scope as TdApi.NotificationSettingsScopeChat).chatId]
//                        if (chat != null) {
//                            synchronized(chat) {
//                                chat.notificationSettings = update.notificationSettings
//                            }
//                        }
//                    }
//                }
//
//                //Update user full info
//                TdApi.UpdateUserFullInfo.CONSTRUCTOR ->{
//                    val updateUserFullInfo = tdEvent as TdApi.UpdateUserFullInfo
//                    usersFullInfo[updateUserFullInfo.userId] = updateUserFullInfo.userFullInfo
//                }
//
//                //Update basic group full info
//                TdApi.UpdateBasicGroupFullInfo.CONSTRUCTOR ->{
//                    val updateBasicGroupFullInfo = tdEvent as TdApi.UpdateBasicGroupFullInfo
//                    basicGroupsFullInfo[updateBasicGroupFullInfo.basicGroupId] = updateBasicGroupFullInfo.basicGroupFullInfo
//                }
//
//                //Update supergroup info
//                TdApi.UpdateSupergroupFullInfo.CONSTRUCTOR ->{
//                    val updateSupergroupFullInfo = tdEvent as TdApi.UpdateSupergroupFullInfo
//                    supergroupsFullInfo[updateSupergroupFullInfo.supergroupId] = updateSupergroupFullInfo.supergroupFullInfo
//                }
//
//
//                else -> println("ATTENTION Unsupported update event. Recieved: $tdEvent")
//            }
//        }
    }
}