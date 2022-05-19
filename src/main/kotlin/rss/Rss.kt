package rss

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * https://www.rssboard.org/rss-specification
 */
@JsonRootName("rss")
data class Rss(

    @JvmField
    @field:JacksonXmlProperty
    val channel: Channel = Channel(),

    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    val version: String = "2.0.1"
)