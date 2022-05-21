package dev.patbeagan.protocolrss.util.base

import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.util.RssString

interface RssSerializer {
    fun serialize(data: Rss): RssString
}
