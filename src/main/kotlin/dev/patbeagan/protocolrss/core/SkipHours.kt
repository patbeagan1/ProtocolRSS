package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * A hint for aggregators telling them which hours they can skip.
 * This element contains up to 24 <hour> sub-elements whose value is a number between 0 and 23,
 * representing a time in GMT, when aggregators, if they support the feature,
 * may not read the channel on hours listed in the <skipHours> element.
 *
 * The hour beginning at midnight is hour zero.
 */
data class SkipHours(
    @JvmField
    @field:JacksonXmlProperty
    val hour: List<Int>,
)
