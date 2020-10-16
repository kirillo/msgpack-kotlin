package com.evnmo.msgpack


class MessagePack {
    fun <T> encodeToBinary(value: T): ByteArray {
        return ByteArray(1)
    }

    fun <T> decodeFromBinary(bytes: ByteArray): T? {
        return null
    }
}