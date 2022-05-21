package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import dev.patbeagan.protocolrss.util.RssFileReader
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

    fun serializeToXML(rssWriter: RssWriter = RssWriter()) = rssWriter.serialize(this)
    suspend fun writeToFile(
        file: File,
        rssWriter: RssWriter = RssWriter(),
    ) = serializeToXML(rssWriter).writeToFile(file)

    companion object {
        suspend fun from(
            file: File,
            rssReader: RssFileReader = RssFileReader(),
            config: Rss.() -> Unit = {},
        ): Rss? = rssReader.readFromFile(file)?.apply { config(this) }

        fun from(
            input: String,
            rssReader: RssReader = RssReader(),
            config: Rss.() -> Unit = {},
        ): Rss? = rssReader.deserializeOrNull(input)?.apply { config(this) }

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
