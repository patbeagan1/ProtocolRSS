import dev.patbeagan.protocolrss.core.Category
import dev.patbeagan.protocolrss.core.Channel
import dev.patbeagan.protocolrss.core.Cloud
import dev.patbeagan.protocolrss.core.Enclosure
import dev.patbeagan.protocolrss.core.Guid
import dev.patbeagan.protocolrss.core.Image
import dev.patbeagan.protocolrss.core.Item
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.core.SkipDays
import dev.patbeagan.protocolrss.core.Source
import dev.patbeagan.protocolrss.util.RssWriter
import org.junit.jupiter.api.Test
import java.io.File
import java.time.Instant

internal class PrintTest {
    private val rssWriter = RssWriter()

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
                    protocol = Cloud.Protocol.REST
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
