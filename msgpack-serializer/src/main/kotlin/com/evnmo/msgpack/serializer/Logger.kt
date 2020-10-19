package com.evnmo.msgpack.serializer


internal class Logger(private val config: MessagePackConf) {

    fun log(message: String) {
        if (config.useDebugLogging) println("DEBUG: MessagePack: $message")
    }
}