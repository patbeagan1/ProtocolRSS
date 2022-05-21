package dev.patbeagan.rss

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * A channel may optionally contain a <textInput> sub-element,
 * which contains four required sub-elements.
 *
 * The purpose of the `<textInput>` element is something of a mystery.
 * You can use it to specify a search engine box.
 * Or to allow a reader to provide feedback.
 * Most aggregators ignore it.
 */
data class TextInput(
    /**
     *  `<description>` -- Explains the text input area.
     */
    @JvmField
    @field:JacksonXmlProperty
    var description: String,
    /**
     *  `<link>` -- The URL of the CGI script that processes text input requests.
     */
    @JvmField
    @field:JacksonXmlProperty
    var link: String,
    /**
     *  `<name>` -- The name of the text object in the text input area.
     */
    @JvmField
    @field:JacksonXmlProperty
    var name: String,
    /**
     *  `<title>` -- The label of the Submit button in the text input area.
     */
    @JvmField
    @field:JacksonXmlProperty
    var title: String,
)
