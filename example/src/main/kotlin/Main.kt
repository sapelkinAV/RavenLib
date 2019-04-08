package com.sapelkinav

import com.beust.klaxon.Klaxon
import com.sapelkinav.com.sapelkinav.revenlib.entities.Config
import com.sapelkinav.ravenlib.RavenLib
import com.sapelkinav.ravenlib.client.TdlibParameters
import com.sapelkinav.ravenlib.model.chat.ChatRepository
import org.drinkless.tdlib.TdApi
import java.io.File

//val API_ID = System.getenv("APPLICATION_ID").toInt()
//val APP_HASH = System.getenv("APPLICATION_HASH")
//val PHONE = System.getenv("PHONE")


fun main(args: Array<String>) {

    val config: Config = Klaxon()
        .parse<Config>(File("./config.json"))!!

    val tdlibParameters = TdlibParameters(
        apiId = config.appId,
        apiHash = config.appHash
    ).apply {
        databaseDirectory = "./${config.phone}"
    }

    val ravenLib = RavenLib(
        //Parameters
        tdlibParameters,
        //Function to get the phone
        config.phone,

        //Function to get the code
        {
            println("Enter the code")
            readLine().toString()
        },
        { "" }

    )

    val client = ravenLib.ravenClient
    val chatRepository = ChatRepository(client)
    val superGroups = chatRepository.getSuperGroupChats()
    val group = chatRepository.searchPublicChat("archiveOfAllMusic")
    val messages = group.getAudioMessages(limit = 51)
    ravenLib.tdEvents.subscribe {
        when (it.constructor) {
            TdApi.UpdateNewMessage.CONSTRUCTOR -> {
                val message = it as TdApi.UpdateNewMessage
                println(it)
            }

        }
    }

    println("Count of audios is ${messages.size}")


}

