package rss

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class RssReader(
    private val xmlMapper: XmlMapper = XmlMapper(
        JacksonXmlModule().apply { setDefaultUseWrapper(false) }
    ).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        enable(SerializationFeature.FAIL_ON_SELF_REFERENCES)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }
) {
    fun convertToRSS(data: String): Rss? = try {
        xmlMapper.readValue(data, Rss::class.java)
    } catch (e: InvalidDefinitionException) {
        println("Invalid XML file.\nFound: $e")
        null
    }

    fun readFromFile(file: File): Rss? {
        try {
            FileInputStream(file).use { fileInputStream ->
                return fileInputStream.readAllBytes().toString().let { convertToRSS(it) }
            }
        } catch (e: FileNotFoundException) {
            println("File Not Found: $e")
        }
        return null
    }
}
