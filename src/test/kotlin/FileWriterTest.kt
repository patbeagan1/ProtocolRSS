import dev.patbeagan.protocolrss.core.Category
import dev.patbeagan.protocolrss.core.Channel
import dev.patbeagan.protocolrss.core.Cloud
import dev.patbeagan.protocolrss.core.Guid
import dev.patbeagan.protocolrss.core.Image
import dev.patbeagan.protocolrss.core.Item
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.core.SkipDays
import dev.patbeagan.protocolrss.util.RssWriter
import org.junit.jupiter.api.Test
import java.io.File
import java.net.URL
import java.time.Instant

internal class FileWriterTest {
    private val rssWriter = RssWriter()

    @Test
    fun `test writing to a file works correctly`() {
        val data = Rss(
            channel = Channel(
                "title", URL("https://example.com"), "description", listOf(Category("gardening")),
                Cloud(
                    domain = "domain",
                    port = 80,
                    path = "/tmp/test",
                    registerProcedure = "procedure",
                    protocol = Cloud.Protocol.REST
                ),
                "copyright", "docs", "generator",
                Image(
                    "link",
                    "title",
                    "https://0.gravatar.com/avatar/c63294a8ad8d960a8fa817e0e4f5259c?s=34&d=identicon&r=G",
                    "des",
                    88,
                    100,
                ),
                listOf(
                    Item(

                        title = "my-title",
                        description = "description",
                        author = "author",
                        category = listOf(Category("cats", "cat")),
                        comments = "commenturl",
                        guid = Guid("guid2", true),
                        pubDate = Instant.now().toString(),
                    ),
                    Item(
                        title = "third-title",
                        description = "description3",
                        author = "author",
                        category = listOf(Category("cats", "cat")),
                        comments = "commenturl",
                        guid = Guid("guid23", true),
                        pubDate = Instant.now().toString(),
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
                        author = "author",
                        category = listOf(Category("cats", "cat")),
                        comments = "commenturl",
                        guid = Guid("guid", true),
                        pubDate = Instant.ofEpochSecond(1).toEpochMilli().toString(),
                    )
                ),
                skipDays = SkipDays(
                    listOf(
                        1,
                        2,
                        3,
                    )
                ),
                pubDate = "123"
            )
        )
        rssWriter.writeToFile(data, File("out-feed.xml"))
        println(rssWriter.convertToXML(data))
    }
}
