import dev.patbeagan.protocolrss.core.Category
import dev.patbeagan.protocolrss.core.Cloud
import dev.patbeagan.protocolrss.core.Enclosure
import dev.patbeagan.protocolrss.core.Guid
import dev.patbeagan.protocolrss.core.Image
import dev.patbeagan.protocolrss.core.Item
import dev.patbeagan.protocolrss.core.Rss
import dev.patbeagan.protocolrss.core.SkipDays
import dev.patbeagan.protocolrss.core.SkipHours
import dev.patbeagan.protocolrss.core.Source
import dev.patbeagan.protocolrss.core.TextInput
import org.junit.jupiter.api.Test
import java.net.URL
import kotlin.test.assertEquals

class CreatingCompleteObjectTest {

    @Test
    fun `test creating a complete object`() {
        val sub = Rss.create(
            title = "title", description = "link", link = URL("https://example.com")
        ) {
            title = "Title"
            link = URL("https://example.com")
            description = "Description"
            category = listOf(
                Category(
                    category = "category", domain = "domain"
                )
            )
            cloud = Cloud(
                domain = "https://example.com",
                port = 80,
                path = "/test",
                registerProcedure = "cgi",
                protocol = Cloud.Protocol.REST
            )
            copyright = "2022"
            docs = "doclink"
            generator = "generated by"
            image = Image(
                link = "link",
                title = "title",
                url = "url",
                description = "desc",
                width = 88,
                height = 100,
            )
            item = listOf(
                Item(
                    title = "title",
                    description = "desc",
                    author = "pat",
                    category = listOf(
                        Category(
                            category = "category", domain = "domain"
                        )
                    ),
                    comments = "comment",
                    enclosure = Enclosure(
                        url = URL("https://example.com"),
                        length = 10,
                        type = "image/png",
                    ),
                    guid = Guid(
                        guid = "guid", isPermalink = false
                    ),
                    link = URL("http://www.tomalak.org/links2.xml"),
                    pubDate = "May 20, 2022",
                    source = Source(
                        url = URL("http://www.tomalak.org/links2.xml"),
                        source = "source"
                    ),
                )
            )
            language = "en-US"
            lastBuildDate = "May 20, 2022"
            managingEditor = "Pat"
            pubDate = "May 20, 2022"
            rating = "10"
            skipDays = SkipDays(listOf(1, 2, 3))
            skipHours = SkipHours(listOf(1, 2, 3))
            textInput = TextInput(
                description = "description",
                link = "link",
                name = "name",
                title = "title",
            )
            ttl = 100
            webMaster = "Pat"
        }.serializeToXML().value

        assertEquals(CompleteRSSSpecXML, sub)
    }

    companion object {
        val CompleteRSSSpecXML = """
            <?xml version="1.0"?>
            <rss version="2.0.1">
              <channel>
                <title>Title</title>
                <link>https://example.com</link>
                <description>Description</description>
                <language>en-US</language>
                <pubDate>May 20, 2022</pubDate>
                <lastBuildDate>May 20, 2022</lastBuildDate>
                <docs>doclink</docs>
                <generator>generated by</generator>
                <managingEditor>Pat</managingEditor>
                <webMaster>Pat</webMaster>
                <item>
                  <title>title</title>
                  <link>http://www.tomalak.org/links2.xml</link>
                  <description>desc</description>
                  <pubDate>May 20, 2022</pubDate>
                  <guid isPermalink="false">guid</guid>
                  <author>pat</author>
                  <category domain="domain">category</category>
                  <comments>comment</comments>
                  <enclosure url="https://example.com" length="10" type="image/png"/>
                  <source url="http://www.tomalak.org/links2.xml">source</source>
                </item>
                <category domain="domain">category</category>
                <cloud domain="https://example.com" port="80" path="/test" registerProcedure="cgi" protocol="http-post"/>
                <copyright>2022</copyright>
                <image>
                  <link>link</link>
                  <title>title</title>
                  <url>url</url>
                  <description>desc</description>
                  <width>88</width>
                  <height>100</height>
                </image>
                <rating>10</rating>
                <skipDays>
                  <day>1</day>
                  <day>2</day>
                  <day>3</day>
                </skipDays>
                <skipHours>
                  <hour>1</hour>
                  <hour>2</hour>
                  <hour>3</hour>
                </skipHours>
                <textInput>
                  <description>description</description>
                  <link>link</link>
                  <name>name</name>
                  <title>title</title>
                </textInput>
                <ttl>100</ttl>
              </channel>
            </rss>

        """.trimIndent()
    }
}
