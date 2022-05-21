package dev.patbeagan.rss

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

/**
 * `<category>` is an optional sub-element of `<item>`.
 *
 * It has one optional attribute, domain, a string that identifies a categorization taxonomy.
 *
 * The value of the element is a forward-slash-separated string that
 * identifies a hierarchic location in the indicated taxonomy.
 * Processors may establish conventions for the interpretation of categories.
 * Two examples are provided below:
 * ```
 * <category>Grateful Dead</category>
 * <category domain="http://www.fool.com/cusips">MSFT</category>
 * ```
 * You may include as many category elements as you need to, for different domains,
 * and to have an item cross-referenced in different parts of the same domain.
 */
data class Category(

    /**
     * The name of the current category
     */
    @JvmField
    @field:JacksonXmlText
    var category: String,

    /**
     * identifies a categorization taxonomy.
     */
    @JvmField
    @field: JacksonXmlProperty(isAttribute = true)
    var domain: String? = null,
)
