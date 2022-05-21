package dev.patbeagan.protocolrss.util

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.util.Const.DefaultXMLMapper
import dev.patbeagan.protocolrss.util.base.RssDeserializer

class RssReader(
    private val xmlMapper: XmlMapper = DefaultXMLMapper,
) : RssDeserializer {

    override fun deserializeOrNull(data: String): Rss? = try {
        deserialize(data)
    } catch (e: InvalidDefinitionException) {
        println("Invalid XML file.\nFound: $e")
        null
    }

    override fun deserialize(data: String): Rss = xmlMapper.readValue(data, Rss::class.java)
}
