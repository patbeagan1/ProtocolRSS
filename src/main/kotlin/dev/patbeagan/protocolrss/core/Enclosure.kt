package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.net.URL

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

    /**
     * The location of the file to be downloaded.
     */
    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var url: URL,

    /**
     * The length of the file to tbe downloaded.
     */
    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var length: Long,

    /**
     * A mime type that indicates the type of file which should be downloaded.
     */
    @JvmField
    @field:JacksonXmlProperty(isAttribute = true)
    var type: String,
)
