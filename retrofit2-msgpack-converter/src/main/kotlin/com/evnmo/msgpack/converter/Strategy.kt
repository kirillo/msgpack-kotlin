package com.evnmo.msgpack.converter

import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.json.Json

public sealed class Strategy {
    public data class AsJson(val json: Json = Json.Default) : Strategy()
    public data class AsMessagePack(val messagePack: MessagePack = MessagePack.Default) : Strategy()
}
