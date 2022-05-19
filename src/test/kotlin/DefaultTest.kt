import org.junit.jupiter.api.Test
import rss.Rss
import rss.RssWriter
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
        val actual = rssWriter.process(Rss())
        assertEquals(expected, actual)
    }
}