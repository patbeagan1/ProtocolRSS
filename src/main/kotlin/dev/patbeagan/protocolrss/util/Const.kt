package dev.patbeagan.protocolrss.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper

object Const {
    const val DefaultString = "UNKNOWN"
    val DefaultXMLMapper = XmlMapper(
        JacksonXmlModule().apply { setDefaultUseWrapper(false) }
    ).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        enable(SerializationFeature.FAIL_ON_SELF_REFERENCES)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }
}
