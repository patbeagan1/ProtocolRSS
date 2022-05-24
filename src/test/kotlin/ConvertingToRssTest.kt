import dev.patbeagan.protocolrss.core.Item
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.util.RssReader
import org.junit.jupiter.api.Test
import java.net.URL
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ConvertingToRssTest {
    private val rssReader = RssReader()

    @Test
    fun `test converting via reader`() {
        val rss = rssReader.deserialize(DemoBlogXML)
        assertEquals(DemoBlogRss, rss)
    }

    @Test
    fun `test converting via Rss-from`() {
        val rss = Rss.from(DemoBlogXML)
        assertNotNull(rss)
        assertEquals(DemoBlogRss, rss)
    }

    companion object {
        private val DemoBlogRss = Rss.create(
            title = "My Blog",
            link = URL("https://example.com"),
            description = "A blog about me"
        ) {
            items = listOf(
                Item(
                    title = "title",
                    description = "desc"
                )
            )
        }

        private val DemoBlogXML = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>My Blog</title>
                <link>https://example.com</link>
                <description>A blog about me</description>
                <item>
                  <title>title</title>
                  <description>desc</description>
                </item>
              </channel>
            </rss>
            
        """.trimIndent()
    }
}
