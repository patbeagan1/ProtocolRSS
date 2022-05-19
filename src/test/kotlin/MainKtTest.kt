
import org.junit.jupiter.api.Test
import rss.Category
import rss.Channel
import rss.Cloud
import rss.Enclosure
import rss.Guid
import rss.Image
import rss.Item
import rss.Rss
import rss.RssWriter
import rss.SkipDays
import rss.Source
import java.io.File
import java.time.Instant

internal class MainKtTest {
    val rssWriter = RssWriter()

    @Test
    fun `test basic functionality`() {
        val data = Rss(
            channel = Channel(
                description = "description",
                link = "link",
                title = "title",
                category = listOf(Category("gardening")),
                skipDays = SkipDays(
                    listOf(
                        1,
                        2,
                        3,
                    )
                ),
                cloud = Cloud(
                    domain = "domain",
                    port = 80,
                    path = "/tmp/test",
                    registerProcedure = "procedure",
                    protocol = "https"
                ),
                copyright = "copyright",
                docs = "docs",
                generator = "generator",
                pubDate = "123",
                image = Image(
                    "link",
                    "title",
                    "https://0.gravatar.com/avatar/c63294a8ad8d960a8fa817e0e4f5259c?s=34&d=identicon&r=G",
                    "des",
                    88,
                    100,
                ),
                item = listOf(
                    Item(
                        "my-title",
                        "description",
                        "author",
                        listOf(Category("cats", "cat")),
                        "commenturl",
                        enclosure = Enclosure("url", "length", "type"),
                        Guid("guid2", true),
                        "link",
                        Instant.now().toString(),
                        Source("url", "source")
                    ),
                    Item(
                        "third-title",
                        "description3",
                        "author",
                        listOf(Category("cats", "cat")),
                        "commenturl",
                        enclosure = Enclosure("url", "length", "type"),
                        Guid("guid23", true),
                        "link",
                        Instant.now().toString(),
                        Source("url", "source")
                    ),
                    Item(
                        "title",
                        """descri
                        <h1>
                            pti
                        </h1>
                        on
                        <img src="https://0.gravatar.com/avatar/c63294a8ad8d960a8fa817e0e4f5259c?s=34&d=identicon&r=G"/>
                        <p>
                            test
                        </p>
                        """.trimIndent(),
                        "author",
                        listOf(Category("cats", "cat")),
                        "commenturl",
                        enclosure = Enclosure("url", "length", "type"),
                        Guid("guid", true),
                        "link",
                        Instant.ofEpochSecond(1).toEpochMilli().toString(),
                        Source("url", "source")
                    )
                )
            )
        )
        rssWriter.writeToFile(data, File("out-feed.xml"))
        println(rssWriter.convertToXML(data))
    }
}
