import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.junit.jupiter.api.Test
import rss.Channel
import rss.Guid
import rss.Item
import rss.Rss
import rss.RssReader
import rss.RssWriter
import kotlin.test.assertEquals

internal class ConversionTest {

    private val xmlMapper = XmlMapper(
        JacksonXmlModule().apply { setDefaultUseWrapper(false) }
    ).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

    private val writer = RssWriter(xmlMapper)
    private val reader = RssReader(xmlMapper)

    /**
     * taken from view-source:https://www.rssboard.org/files/sample-rss-2.xml
     */
    private val xml = """
            <?xml version="1.0"?>
            <rss version="2.0">
              <channel>
                <title>Liftoff News</title>
                <link>http://liftoff.msfc.nasa.gov/</link>
                <description>Liftoff to Space Exploration.</description>
                <language>en-us</language>
                <pubDate>Tue, 10 Jun 2003 04:00:00 GMT</pubDate>
                <lastBuildDate>Tue, 10 Jun 2003 09:41:01 GMT</lastBuildDate>
                <docs>http://blogs.law.harvard.edu/tech/rss</docs>
                <generator>Weblog Editor 2.0</generator>
                <managingEditor>editor@example.com</managingEditor>
                <webMaster>webmaster@example.com</webMaster>
                <item>
                  <title>Star City</title>
                  <link>http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp</link>
                  <description>How do Americans get ready to work with Russians aboard the International Space Station? They take a crash course in culture, language and protocol at Russia's &amp;amp;lt;a href="http://howe.iki.rssi.ru/GCTC/gctc_e.htm"&amp;amp;gt;Star City&amp;amp;lt;/a&amp;amp;gt;.</description>
                  <pubDate>Tue, 03 Jun 2003 09:39:21 GMT</pubDate>
                  <guid>http://liftoff.msfc.nasa.gov/2003/06/03.html#item573</guid>
                </item>
                <item>
                  <description>Sky watchers in Europe, Asia, and parts of Alaska and Canada will experience a &amp;amp;lt;a href="http://science.nasa.gov/headlines/y2003/30may_solareclipse.htm"&amp;amp;gt;partial eclipse of the Sun&amp;amp;lt;/a&amp;amp;gt; on Saturday, May 31st.</description>
                  <pubDate>Fri, 30 May 2003 11:06:42 GMT</pubDate>
                  <guid>http://liftoff.msfc.nasa.gov/2003/05/30.html#item572</guid>
                </item>
                <item>
                  <title>The Engine That Does More</title>
                  <link>http://liftoff.msfc.nasa.gov/news/2003/news-VASIMR.asp</link>
                  <description>Before man travels to Mars, NASA hopes to design new engines that will let us fly through the Solar System more quickly.  The proposed VASIMR engine would do that.</description>
                  <pubDate>Tue, 27 May 2003 08:37:32 GMT</pubDate>
                  <guid>http://liftoff.msfc.nasa.gov/2003/05/27.html#item571</guid>
                </item>
                <item>
                  <title>Astronauts' Dirty Laundry</title>
                  <link>http://liftoff.msfc.nasa.gov/news/2003/news-laundry.asp</link>
                  <description>Compared to earlier spacecraft, the International Space Station has many luxuries, but laundry facilities are not one of them.  Instead, astronauts have other options.</description>
                  <pubDate>Tue, 20 May 2003 08:56:02 GMT</pubDate>
                  <guid>http://liftoff.msfc.nasa.gov/2003/05/20.html#item570</guid>
                </item>
              </channel>
            </rss>
            
    """.trimIndent()

    private val kotlin = Rss(
        version = "2.0",
        channel = Channel(
            title = "Liftoff News",
            link = "http://liftoff.msfc.nasa.gov/",
            description = "Liftoff to Space Exploration.",
            language = "en-us",
            pubDate = "Tue, 10 Jun 2003 04:00:00 GMT",
            lastBuildDate = "Tue, 10 Jun 2003 09:41:01 GMT",
            docs = "http://blogs.law.harvard.edu/tech/rss",
            generator = "Weblog Editor 2.0",
            managingEditor = "editor@example.com",
            webMaster = "webmaster@example.com",
            item = listOf(
                Item(
                    title = "Star City",
                    link = "http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp",
                    description = "How do Americans get ready to work with Russians aboard the International Space Station? They take a crash course in culture, language and protocol at Russia's &amp;lt;a href=\"http://howe.iki.rssi.ru/GCTC/gctc_e.htm\"&amp;gt;Star City&amp;lt;/a&amp;gt;.",
                    pubDate = "Tue, 03 Jun 2003 09:39:21 GMT",
                    guid = Guid("http://liftoff.msfc.nasa.gov/2003/06/03.html#item573"),
                ),
                Item(
                    description = "Sky watchers in Europe, Asia, and parts of Alaska and Canada will experience a &amp;lt;a href=\"http://science.nasa.gov/headlines/y2003/30may_solareclipse.htm\"&amp;gt;partial eclipse of the Sun&amp;lt;/a&amp;gt; on Saturday, May 31st.",
                    pubDate = "Fri, 30 May 2003 11:06:42 GMT",
                    guid = Guid("http://liftoff.msfc.nasa.gov/2003/05/30.html#item572"),
                ),
                Item(
                    title = "The Engine That Does More",
                    link = "http://liftoff.msfc.nasa.gov/news/2003/news-VASIMR.asp",
                    description = "Before man travels to Mars, NASA hopes to design new engines that will let us fly through the Solar System more quickly.  The proposed VASIMR engine would do that.",
                    pubDate = "Tue, 27 May 2003 08:37:32 GMT",
                    guid = Guid("http://liftoff.msfc.nasa.gov/2003/05/27.html#item571"),
                ),
                Item(
                    title = "Astronauts' Dirty Laundry",
                    link = "http://liftoff.msfc.nasa.gov/news/2003/news-laundry.asp",
                    description = "Compared to earlier spacecraft, the International Space Station has many luxuries, but laundry facilities are not one of them.  Instead, astronauts have other options.",
                    pubDate = "Tue, 20 May 2003 08:56:02 GMT",
                    guid = Guid("http://liftoff.msfc.nasa.gov/2003/05/20.html#item570"),
                )
            )
        )
    )

    @Test
    fun `test conversion to xml`() {
        assertEquals(xml, writer.convertToXML(kotlin))
    }

    @Test
    fun `test conversion to kotlin`() {
        assertEquals(kotlin, reader.convertToRSS(xml))
    }
}
