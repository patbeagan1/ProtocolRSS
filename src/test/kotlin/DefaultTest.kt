import org.junit.jupiter.api.Test
import dev.patbeagan.rss.Rss
import dev.patbeagan.rss.RssWriter
import kotlin.test.assertEquals

class DefaultTest {
    private val rssWriter = RssWriter()

    @Test
    fun `test minimal rss`() {
        val expected = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>UNKNOWN</title>
                <link>UNKNOWN</link>
                <description>UNKNOWN</description>
              </channel>
            </rss>
            
        """.trimIndent()
        val actual = rssWriter.convertToXML(Rss())
        assertEquals(expected, actual)
    }
}
