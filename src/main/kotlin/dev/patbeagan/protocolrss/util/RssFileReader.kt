package dev.patbeagan.protocolrss.util

import dev.patbeagan.protocolrss.core.Rss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class RssFileReader(private val rssReader: RssReader = RssReader()) {
    suspend fun readFromFile(file: File): Rss? {
        try {
            return withContext(Dispatchers.IO) {
                FileInputStream(file)
                    .use { fileInputStream ->
                        fileInputStream
                            .reader()
                            .readText()
                            .let { rssReader.deserializeOrNull(it) }
                    }
            }
        } catch (e: FileNotFoundException) {
            println("File Not Found: $e")
        }
        return null
    }
}
