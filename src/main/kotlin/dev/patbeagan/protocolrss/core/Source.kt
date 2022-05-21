package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import java.net.URL

/**
 * `<source>` is an optional sub-element of `<item>`.
 *
 * Its value is the name of the RSS channel that the item came from, derived from its <title>.
 * It has one required attribute, url, which links to the XMLization of the source.
 * ```
 * <source url="http://www.tomalak.org/links2.xml">Tomalak's Realm</source>
 * ```
 * The purpose of this element is to propagate credit for links, to publicize the sources of news items.
 * It can be used in the Post command of an aggregator.
 * It should be generated automatically when forwarding an item from an aggregator to a weblog authoring tool.
 */
data class Source(
    /**
     * Links back to the source that this item comes from.
     */
    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var url: URL,
    /**
     * The name of the source that this item comes from.
     */
    @JvmField
    @field:JacksonXmlText
    var source: String,
)
