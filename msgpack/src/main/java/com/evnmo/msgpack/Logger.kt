package com.evnmo.msgpack


internal class Logger(private val config: MessagePackConf) {

    fun log(message: String) {
        if (BuildConfig.DEBUG && config.useDebugLogging) println("DEBUG: MessagePack: $message")
    }
}