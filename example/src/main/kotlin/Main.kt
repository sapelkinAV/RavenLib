package com.sapelkinav

import com.beust.klaxon.Klaxon
import com.sapelkinav.ravenlib.RavenLib
import com.sapelkinav.ravenlib.client.TdlibParameters
import com.sapelkinav.revenlib.entities.Config
import org.drinkless.tdlib.TdApi
import java.io.File

//val API_ID = System.getenv("APPLICATION_ID").toInt()
//val APP_HASH = System.getenv("APPLICATION_HASH")
//val PHONE = System.getenv("PHONE")


fun main() {

    val config: Config = Klaxon()
        .parse<Config>(File("./config.json"))!!

    val tdlibParameters = TdlibParameters(
        apiId = config.appId,
        apiHash = config.appHash,
        databaseDirectory = "./${config.phone}"
    )

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

    ravenLib.errorEvents.subscribe {
        println("Beda prikluchilas ${it.printStackTrace()}")
    }


    val client = ravenLib.ravenClient
    val chatRepository = ravenLib.chatRepository

    val allrockChannel = chatRepository.searchPublicChat("AllRock")

    val ROOOOOOOOK = allrockChannel.getMessagesAsync(filter = TdApi.SearchMessagesFilterAudio())
        .toList()
        .blockingGet()

    ROOOOOOOOK.forEach {
        println(it)
    }


}






