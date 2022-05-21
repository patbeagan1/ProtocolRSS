package dev.patbeagan.rss

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * `<enclosure>` is an optional sub-element of `<item>`.
 *
 * It has three required attributes. url says where the enclosure is located, length says how big it is in bytes, and type says what its type is, a standard MIME type.
 *
 * The url must be an http url.
 *```
 * <enclosure url="http://www.scripting.com/mp3s/weatherReportSuite.mp3" length="12216320" type="audio/mpeg" />
 *```
 * A use-case narrative for this element is [here](https://www.rssboard.org/rss-enclosures-use-case).
 */
data class Enclosure(

    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var url: String,

    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var length: String,

    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var type: String,
)
