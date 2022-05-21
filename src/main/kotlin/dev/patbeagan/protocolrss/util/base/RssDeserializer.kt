package dev.patbeagan.protocolrss.util.base

import dev.patbeagan.protocolrss.core.Rss

interface RssDeserializer {
    fun deserializeOrNull(data: String): Rss?
    fun deserialize(data: String): Rss
}
