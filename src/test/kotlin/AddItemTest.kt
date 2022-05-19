import org.junit.jupiter.api.Test
import rss.Item
import rss.Rss
import rss.RssReader
import rss.RssWriter
import kotlin.test.assertEquals

class AddItemTest {
    private val rssWriter = RssWriter()
    private val rssReader = RssReader()

    @Test
    fun `test adding an item`() {
        val original = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>My BLog</title>
                <link>UNKNOWN</link>
                <description>UNKNOWN</description>
              </channel>
            </rss>
            
        """.trimIndent()

        val expected = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>My BLog</title>
                <link>UNKNOWN</link>
                <description>UNKNOWN</description>
                <item>
                  <title>title</title>
                  <description>desc</description>
                </item>
              </channel>
            </rss>
            
        """.trimIndent()

        val rss = rssReader.convertToRSS(original)!!
        val actual = rss.apply {
            addItem(
                Item(title = "title", description = "desc")
            )
        }.let {
            rssWriter.convertToXML(it)
        }

        val second = Rss.from(actual)?.apply {
            addItem(Item("title2", "desc2"))
        }?.toXML()
        println(second)

        assertEquals(expected, actual)
    }
}
