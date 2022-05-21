package rss

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonPropertyOrder(
    "title",
    "link",
    "description",
    "pubDate",
    "guid",
)
/**
 * A channel may contain any number of <item>s.
 * An item may represent a "story" -- much like a story in a newspaper or magazine; if so its description is a synopsis of the story, and the link points to the full story.
 * An item may also be complete in itself, if so, the description contains the text (entity-encoded HTML is allowed; see examples), and the link and title may be omitted.
 * All elements of an item are optional, however at least one of title or description must be present.
 *
 * **title OR description is required**
 */
data class Item(

    /**
     * The title of the item.
     */
    @JvmField
    @field:JacksonXmlProperty
    var title: String? = null,

    /**
     * The item synopsis.
     */
    @JvmField
    @field:JacksonXmlProperty
    var description: String? = null,

    /**
     * Email address of the author of the item.
     *
     * `<author>` is an optional sub-element of `<item>`.
     *
     * It's the email address of the author of the item.
     * For newspapers and magazines syndicating via RSS,
     * the author is the person who wrote the article that the `<item>` describes.
     * For collaborative weblogs, the author of the item might be different from the managing editor or webmaster.
     * For a weblog authored by a single individual it would make sense to omit the `<author>` element.
     *
     * ```
     * <author>lawyer@boyer.net (Lawyer Boyer)</author>
     * ```
     * [More](https://www.rssboard.org/rss-specification#ltauthorgtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var author: String? = null,

    /**
     * Includes the item in one or more categories.
     * [More](https://www.rssboard.org/rss-specification#ltcategorygtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var category: List<Category>? = null,

    /**
     * URL of a page for comments relating to the item.
     *
     * `<comments>` is an optional sub-element of `<item>`.
     *
     * If present, it is the url of the comments page for the item.
     *```
     *  <comments>http://ekzemplo.com/entry/4403/comments</comments>
     *```
     * More about comments [here](https://www.rssboard.org/rss-weblog-comments-use-case).
     * And [here](https://www.rssboard.org/rss-specification#ltcommentsgtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var comments: String? = null,

    /**
     * Describes a media object that is attached to the item.
     * [More](https://www.rssboard.org/rss-specification#ltenclosuregtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var enclosure: Enclosure? = null,

    /**
     * A string that uniquely identifies the item.
     * [More](https://www.rssboard.org/rss-specification#ltguidgtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var guid: Guid? = null,

    /**
     * The URL of the item.
     */
    @JvmField
    @field:JacksonXmlProperty
    var link: String? = null,

    /**
     * Indicates when the item was published.
     *
     * `<pubDate>` is an optional sub-element of `<item>`.
     *
     * Its value is a date, indicating when the item was published. If it's a date in the future, aggregators may choose to not display the item until that date.
     * ```
     * <pubDate>Sun, 19 May 2002 15:21:36 GMT</pubDate>
     * ```
     * [More](https://www.rssboard.org/rss-specification#ltpubdategtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var pubDate: String? = null,

    /**
     * The RSS channel that the item came from.
     * [More](https://www.rssboard.org/rss-specification#ltsourcegtSubelementOfLtitemgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var source: Source? = null,
)
