package dev.patbeagan.protocolrss.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dev.patbeagan.protocolrss.core.Rss
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

interface RssSerializer {
    fun serialize(data: Rss): RssString
}

@JvmInline
value class RssString(val value: String) {
    fun writeToFile(file: File) {
        try {
            FileOutputStream(file).use {
                value.forEach { c -> it.write(c.code) }
            }
        } catch (e: FileNotFoundException) {
            println("File Not Found: $e")
        }
    }
}

class RssWriter(
    private val xmlMapper: XmlMapper = DefaultXMLMapper,
) : RssSerializer {
    override fun serialize(data: Rss) =
        RssString("$header\n${xmlMapper.writeValueAsString(data)}")

    companion object {
        private const val header = "<?xml version=\"1.0\"?>"
        private val DefaultXMLMapper = XmlMapper(
            JacksonXmlModule().apply { setDefaultUseWrapper(false) }
        ).apply {
            enable(SerializationFeature.INDENT_OUTPUT)
            enable(SerializationFeature.FAIL_ON_SELF_REFERENCES)
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
}
