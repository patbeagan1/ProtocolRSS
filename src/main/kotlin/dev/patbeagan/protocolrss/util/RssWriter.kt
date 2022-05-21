package dev.patbeagan.protocolrss.util

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.util.Const.DefaultXMLMapper
import dev.patbeagan.protocolrss.util.base.RssSerializer

class RssWriter(
    private val xmlMapper: XmlMapper = DefaultXMLMapper,
) : RssSerializer {
    override fun serialize(data: Rss) =
        RssString("$header\n${xmlMapper.writeValueAsString(data)}")

    companion object {
        private const val header = "<?xml version=\"1.0\"?>"
    }
}
