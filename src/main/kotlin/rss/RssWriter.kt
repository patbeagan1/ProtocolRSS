package rss

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

class RssWriter(
    private val xmlMapper: XmlMapper = XmlMapper(
        JacksonXmlModule().apply { setDefaultUseWrapper(false) }
    ).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        enable(SerializationFeature.FAIL_ON_SELF_REFERENCES)
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
) {
    fun process(data: Rss) = "$header\n${xmlMapper.writeValueAsString(data)}"

    fun writeToFile(data: Rss, file: File) {
        try {
            FileOutputStream(file).use {
                process(data).forEach { c -> it.write(c.code) }
            }
        } catch (e: FileNotFoundException) {
            println("File Not Found: $e")
        }
    }

    companion object {
        private const val header = "<?xml version=\"1.0\"?>"
    }
}
