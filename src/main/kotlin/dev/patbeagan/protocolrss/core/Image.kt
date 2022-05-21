package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * `<image>` is an optional sub-element of `<channel>,`
 * which contains three required and three optional sub-elements.
 *
 * Optional elements include `<width>` and `<height>`, numbers,
 * indicating the width and height of the image in pixels.
 *
 * `<description>` contains text that is included in the TITLE attribute of the link formed around the image in the HTML rendering.
 */
data class Image(

    /**
     * `<link>` is the URL of the site, when the channel is rendered, the image is a link to the site.
     *
     * (Note, in practice the image `<title>` and `<link>` should have the same value as the channel's `<title>` and `<link>.`
     */
    @JvmField
    @field:JacksonXmlProperty
    var link: String,

    /**
     * `<title>` describes the image, it's used in the ALT attribute of the HTML `<img>` tag when the channel is rendered in HTML.
     */
    @JvmField
    @field:JacksonXmlProperty
    var title: String,

    /**
     * `<url>` is the URL of a GIF, JPEG or PNG image that represents the channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var url: String,

    /**
     * `<description>` contains text that is included in the TITLE attribute of the link formed around the image in the HTML rendering.
     */
    @JvmField
    @field:JacksonXmlProperty
    var description: String? = null,

    /**
     * `<width>` indicates the width of the image in pixels.
     * Maximum value for width is 144, default value is 88.
     */
    @JvmField
    @field:JacksonXmlProperty
    var width: Int? = null,

    /**
     * `<height>` indicates the height of the image in pixels.
     * Maximum value for height is 400, default value is 31.
     */
    @JvmField
    @field:JacksonXmlProperty
    var height: Int? = null,
)
