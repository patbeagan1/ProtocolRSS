import dev.patbeagan.protocolrss.core.Item
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.util.RssReader
import dev.patbeagan.protocolrss.util.RssWriter
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AddItemTest {
    private val rssWriter = RssWriter()
    private val rssReader = RssReader()

    @Test
    fun `test adding an item via a reader and a writer`() {
        val rss = rssReader.convertToRSS(SampleWithoutAnyItems)
        assertNotNull(rss)
        val actual = rss.apply {
            addItem(
                Item(title = "title", description = "desc")
            )
        }.let {
            rssWriter.serialize(it).value
        }
        assertEquals(SampleWithOneItem, actual)
    }

    @Test
    fun `test add item works as expected when converting back to xml`() {
        val actual = Rss.from(SampleWithoutAnyItems) {
            addItem(Item(title = "title", description = "desc"))
        }?.toXML()?.value

        assertEquals(SampleWithOneItem, actual)
    }

    companion object {
        val SampleWithOneItem = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>My BLog</title>
                <link>https://example.com</link>
                <description>UNKNOWN</description>
                <item>
                  <title>title</title>
                  <description>desc</description>
                </item>
              </channel>
            </rss>
            
        """.trimIndent()

        val SampleWithoutAnyItems = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>My BLog</title>
                <link>https://example.com</link>
                <description>UNKNOWN</description>
              </channel>
            </rss>
            
        """.trimIndent()
    }
}
