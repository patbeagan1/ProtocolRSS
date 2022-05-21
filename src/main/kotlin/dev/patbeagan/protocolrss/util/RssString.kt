package dev.patbeagan.protocolrss.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

@JvmInline
value class RssString(val value: String) {
    /**
     * Writes the [RssString] to a file. Uses [Dispatchers.IO].
     */
    suspend fun writeToFile(file: File) {
        try {
            withContext(Dispatchers.IO) {
                FileOutputStream(file).use {
                    value.forEach { c -> it.write(c.code) }
                }
            }
        } catch (e: FileNotFoundException) {
            println("File Not Found: $e")
        }
    }
}
