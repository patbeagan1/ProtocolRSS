package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * A hint for aggregators telling them which days they can skip.
 * This element contains up to seven <day> sub-elements whose value is
 * Monday, Tuesday, Wednesday, Thursday, Friday, Saturday or Sunday.
 * Aggregators may not read the channel during days listed in the <skipDays> element.
 *
 */
data class SkipDays(
    @JvmField
    @field:JacksonXmlProperty
    val day: List<Int>
)
