import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.util.RssWriter
import org.junit.jupiter.api.Test
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
                <link>https://example.com</link>
                <description>UNKNOWN</description>
              </channel>
            </rss>
            
        """.trimIndent()
        val actual = rssWriter.serialize(Rss()).value
        assertEquals(expected, actual)
    }
}
