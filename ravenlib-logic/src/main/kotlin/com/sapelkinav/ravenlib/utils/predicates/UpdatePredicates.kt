package com.sapelkinav.ravenlib.utils.predicates

import org.drinkless.tdlib.TdApi


fun updateAuthorizationStatePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateAuthorizationState.CONSTRUCTOR
}
fun updateNewMessagePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewMessage.CONSTRUCTOR
}
fun updateMessageSendAcknowledgedPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageSendAcknowledged.CONSTRUCTOR
}
fun updateMessageSendSucceededPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageSendSucceeded.CONSTRUCTOR
}
fun updateMessageSendFailedPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageSendFailed.CONSTRUCTOR
}
fun updateMessageContentPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageContent.CONSTRUCTOR
}
fun updateMessageEditedPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageEdited.CONSTRUCTOR
}
fun updateMessageViewsPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageViews.CONSTRUCTOR
}
fun updateMessageContentOpenedPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageContentOpened.CONSTRUCTOR
}
fun updateMessageMentionReadPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateMessageMentionRead.CONSTRUCTOR
}
fun updateNewChatPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewChat.CONSTRUCTOR
}
fun updateChatTitlePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatTitle.CONSTRUCTOR
}
fun updateChatPhotoPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatPhoto.CONSTRUCTOR
}
fun updateChatLastMessagePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatLastMessage.CONSTRUCTOR
}
fun updateChatOrderPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatOrder.CONSTRUCTOR
}
fun updateChatIsPinnedPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatIsPinned.CONSTRUCTOR
}
fun updateChatReadInboxPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatReadInbox.CONSTRUCTOR
}
fun updateChatReadOutboxPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatReadOutbox.CONSTRUCTOR
}
fun updateChatUnreadMentionCountPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatUnreadMentionCount.CONSTRUCTOR
}
fun updateNotificationSettingsPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNotificationSettings.CONSTRUCTOR
}
fun updateChatReplyMarkupPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatReplyMarkup.CONSTRUCTOR
}
fun updateChatDraftMessagePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateChatDraftMessage.CONSTRUCTOR
}
fun updateDeleteMessagesPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateDeleteMessages.CONSTRUCTOR
}
fun updateUserChatActionPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateUserChatAction.CONSTRUCTOR
}
fun updateUserStatusPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateUserStatus.CONSTRUCTOR
}
fun updateUserPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateUser.CONSTRUCTOR
}
fun updateBasicGroupPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateBasicGroup.CONSTRUCTOR
}
fun updateSupergroupPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateSupergroup.CONSTRUCTOR
}
fun updateSecretChatPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateSecretChat.CONSTRUCTOR
}
fun updateUserFullInfoPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateUserFullInfo.CONSTRUCTOR
}
fun updateBasicGroupFullInfoPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateBasicGroupFullInfo.CONSTRUCTOR
}
fun updateSupergroupFullInfoPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateSupergroupFullInfo.CONSTRUCTOR
}
fun updateServiceNotificationPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateServiceNotification.CONSTRUCTOR
}
fun updateFilePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateFile.CONSTRUCTOR
}
fun updateFileGenerationStartPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateFileGenerationStart.CONSTRUCTOR
}
fun updateFileGenerationStopPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateFileGenerationStop.CONSTRUCTOR
}
fun updateCallPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateCall.CONSTRUCTOR
}
fun updateUserPrivacySettingRulesPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateUserPrivacySettingRules.CONSTRUCTOR
}
fun updateUnreadMessageCountPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateUnreadMessageCount.CONSTRUCTOR
}
fun updateOptionPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateOption.CONSTRUCTOR
}
fun updateInstalledStickerSetsPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateInstalledStickerSets.CONSTRUCTOR
}
fun updateTrendingStickerSetsPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateTrendingStickerSets.CONSTRUCTOR
}
fun updateRecentStickersPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateRecentStickers.CONSTRUCTOR
}
fun updateFavoriteStickersPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateFavoriteStickers.CONSTRUCTOR
}
fun updateSavedAnimationsPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateSavedAnimations.CONSTRUCTOR
}
fun updateConnectionStatePredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateConnectionState.CONSTRUCTOR
}
fun updateNewInlineQueryPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewInlineQuery.CONSTRUCTOR
}
fun updateNewChosenInlineResultPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewChosenInlineResult.CONSTRUCTOR
}
fun updateNewCallbackQueryPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewCallbackQuery.CONSTRUCTOR
}
fun updateNewInlineCallbackQueryPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewInlineCallbackQuery.CONSTRUCTOR
}
fun updateNewShippingQueryPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewShippingQuery.CONSTRUCTOR
}
fun updateNewPreCheckoutQueryPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewPreCheckoutQuery.CONSTRUCTOR
}
fun updateNewCustomEventPredicate (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewCustomEvent.CONSTRUCTOR
}
fun updateNewCustomQueryPredicate  (tdapiObject:TdApi.Object): Boolean {
    return tdapiObject.constructor == TdApi.UpdateNewCustomQuery.CONSTRUCTOR
}