package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import dev.patbeagan.protocolrss.util.RssReader
import dev.patbeagan.protocolrss.util.RssWriter
import java.io.File
import java.net.URL

/**
 * https://www.rssboard.org/rss-specification
 */
@JsonRootName("rss")
data class Rss(

    @JvmField
    @field:JacksonXmlProperty
    var channel: Channel = Channel(),

    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var version: String = "2.0.1",
) {
    fun addItem(item: Item) {
        val itemList = this.channel.item
        this.channel.item = if (itemList == null) {
            listOf(item)
        } else {
            itemList + item
        }
    }

    fun toXML(rssWriter: RssWriter = RssWriter()) = rssWriter.serialize(this)
    fun writeToFile(
        file: File,
        rssWriter: RssWriter = RssWriter(),
    ) = toXML(rssWriter).writeToFile(file)

    companion object {
        fun from(
            file: File,
            rssReader: RssReader = RssReader(),
        ): Rss? = rssReader.readFromFile(file)

        fun from(
            input: String,
            rssReader: RssReader = RssReader(),
            config: Rss.() -> Unit = {},
        ): Rss? = rssReader.convertToRSS(input)?.apply { config(this) }

        fun create(
            title: String,
            description: String,
            link: URL,
            config: Channel.() -> Unit,
        ): Rss = Rss(
            Channel(
                title = title,
                link = link,
                description = description
            ).apply(config)
        )
    }
}
