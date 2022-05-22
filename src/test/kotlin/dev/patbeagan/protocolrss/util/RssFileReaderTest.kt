package dev.patbeagan.protocolrss.util

import dev.patbeagan.protocolrss.core.Channel
import dev.patbeagan.protocolrss.core.Guid
import dev.patbeagan.protocolrss.core.Item
import dev.patbeagan.protocolrss.core.Rss
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.net.URL

internal class RssFileReaderTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test reading from file`() = runTest {
        val resource: URL = this::class.java.classLoader.getResource("file-reader-test-feed.xml")
        println(resource)
        val toURI = resource.toURI()
        val rss = RssFileReader().readFromFile(File(toURI))
        assertEquals(RssContents, rss)
    }

    companion object {
        val RssContents = Rss(
            channel = Channel(
                title = "title",
                link = URL("https://example.com"),
                description = "description",
                copyright = "copyright",
                docs = "docs",
                generator = "generator",
                item = listOf(
                    Item(
                        title = "my-title",
                        description = "description",
                        author = "author",
                        comments = "commenturl",
                        guid = Guid("guid2", true),
                        pubDate = "now",
                    ),
                    Item(
                        title = "third-title",
                        description = "description3",
                        author = "author",
                        comments = "commenturl",
                        guid = Guid("guid23", true),
                        pubDate = "now",
                    ),
                    Item(
                        "title",
                        "description",
                        author = "author",
                        comments = "commenturl",
                        guid = Guid("guid", true),
                        pubDate = "now",
                    )
                ),
                pubDate = "now"
            )
        )
        const val FileContents = """<?xml version="1.0"?>
<rss version="2.0.1">
  <channel>
    <title>title</title>
    <link>https://example.com</link>
    <description>description</description>
    <pubDate>123</pubDate>
    <docs>docs</docs>
    <generator>generator</generator>
    <item>
      <title>my-title</title>
      <description>description</description>
      <pubDate>2022-05-21T15:07:03.815729Z</pubDate>
      <guid isPermlink="true">guid2</guid>
      <author>author</author>
     
      <comments>commenturl</comments>
    </item>
    <item>
      <title>third-title</title>
      <description>description3</description>
      <pubDate>2022-05-21T15:07:03.817807Z</pubDate>
      <guid isPermlink="true">guid23</guid>
      <author>author</author>
     
      <comments>commenturl</comments>
    </item>
    <item>
      <title>title</title>
      <description>descri
                                        &lt;h1>
                                            pti
                                        &lt;/h1>
                                        on
                                        &lt;img src="https://0.gravatar.com/avatar/c63294a8ad8d960a8fa817e0e4f5259c?s=34&amp;d=identicon&amp;r=G"/>
                                        &lt;p>
                                            test
                                        &lt;/p></description>
      <pubDate>1000</pubDate>
      <guid isPermlink="true">guid</guid>
      <author>author</author>
     
      <comments>commenturl</comments>
    </item>
   
    <cloud domain="domain" port="80" path="/tmp/test" registerProcedure="procedure" protocol="http-post"/>
    <copyright>copyright</copyright>
    <image>
      <link>link</link>
      <title>title</title>
      <url>https://0.gravatar.com/avatar/c63294a8ad8d960a8fa817e0e4f5259c?s=34&amp;d=identicon&amp;r=G</url>
      <description>des</description>
      <width>88</width>
      <height>100</height>
    </image>
    <skipDays>
      <day>1</day>
      <day>2</day>
      <day>3</day>
    </skipDays>
  </channel>
</rss>
"""
    }
}
