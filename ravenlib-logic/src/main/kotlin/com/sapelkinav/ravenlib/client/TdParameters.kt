package com.sapelkinav.ravenlib.client

import org.drinkless.tdlib.TdApi

/**
 * Contains parameters for TDLib initialization.
 */
data class TdlibParameters(
        /**
         * Application identifier for Telegram API access, which can be obtained at https://my.telegram.org.
         */
        val apiId: Int = 0,
        /**
         * Application identifier hash for Telegram API access, which can be obtained at https://my.telegram.org.
         */
        val apiHash: String = "",
        /**
         * The path to the directory for the persistent database; if empty, the current working directory will be used.
         */
        val databaseDirectory: String = "tdlib",
        /**
         * The path to the directory for storing files; if empty, databaseDirectory will be used.
         */
        val filesDirectory: String = "",

        /**
         * If set to true, old files will automatically be deleted.
         */
        val enableStorageOptimizer: Boolean = true,

        /**
         * If set to true, information about downloaded and uploaded files will be saved between application restarts.
         */
        val useFileDatabase: Boolean = true,
        /**
         * If set to true, the library will maintain a cache of users, basic groups, superGroups, channels and secret chats. Implies useFileDatabase.
         */
        val useChatInfoDatabase: Boolean = true,
        /**
         * If set to true, the library will maintain a cache of chats and messages. Implies useChatInfoDatabase.
         */
        val useMessageDatabase: Boolean = true,
        /**
         * If set to true, support for secret chats will be enabled.
         */
        val useSecretChats: Boolean = true,

        /**
         * IETF language tag of the user's operating system language; must be non-empty.
         */
        val systemLanguageCode: String = "en",
        /**
         * Model of the device the application is being run on; must be non-empty.
         */
        val deviceModel: String = "Desktop",
        /**
         * Version of the operating system the application is being run on; must be non-empty.
         */
        val systemVersion: String = "Unknown",
        /**
         * Application version; must be non-empty.
         */
        val applicationVersion: String = "1.0",

        /**
         * If set to true, original file names will be ignored. Otherwise, downloaded files will be saved under names as close as possible to the original name.
         */
        val ignoreFileNames: Boolean = false,
        /**
         * If set to true, the Telegram test environment will be used instead of the production environment.
         */
        val useTestDc: Boolean = false
)
    {

        /**
         * Identifier uniquely determining type of the object.
         */
        val CONSTRUCTOR = -761520773

        fun convertToTdlibFormat():TdApi.TdlibParameters {
             return TdApi.TdlibParameters(
                    useTestDc,
                    databaseDirectory,
                    filesDirectory,
                    useFileDatabase,
                    useChatInfoDatabase,
                    useMessageDatabase,
                    useSecretChats,
                    apiId,
                    apiHash,
                    systemLanguageCode,
                    deviceModel,
                    systemVersion,
                    applicationVersion,
                    enableStorageOptimizer,
                    ignoreFileNames
            )
        }

}