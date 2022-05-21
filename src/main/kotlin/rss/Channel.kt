package rss

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import rss.Const.defaultString

@JsonPropertyOrder(
    "title",
    "link",
    "description",
    "language",
    "pubDate",
    "lastBuildDate",
    "docs",
    "generator",
    "managingEditor",
    "webMaster",
    "item",
)
data class Channel(

    /**
     * The name of the channel. It's how people refer to your service.
     * If you have an HTML website that contains the same information as your RSS file,
     * the title of your channel should be the same as the title of your website.
     */
    @JvmField
    @field:JacksonXmlProperty
    var title: String = defaultString,

    /**
     * The URL to the HTML website corresponding to the channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var link: String = defaultString,

    /**
     * Phrase or sentence describing the channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var description: String = defaultString,

    /**
     * Specify one or more categories that the channel belongs to.
     * Follows the same rules as the <item>-level [category](https://www.rssboard.org/rss-specification#ltcategorygtSubelementOfLtitemgt) element.
     * [More info](https://www.rssboard.org/rss-specification#syndic8).
     */
    @JvmField
    @field:JacksonXmlProperty
    var category: List<Category>? = null,

    /**
     * Allows processes to register with a cloud to be notified of updates to the channel,
     * implementing a lightweight publish-subscribe protocol for RSS feeds.
     * More info [here](https://www.rssboard.org/rss-specification#ltcloudgtSubelementOfLtchannelgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var cloud: Cloud? = null,

    /**
     * Copyright notice for content in the channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var copyright: String? = null,

    /**
     * A URL that points to the [documentation](https://www.rssboard.org/rss-specification) for the format used in the RSS file.
     * It's probably a pointer to this page.
     * It's for people who might stumble across an RSS file on a Web server 25 years from now and wonder what it is.
     */
    @JvmField
    @field:JacksonXmlProperty
    var docs: String? = null,

    /**
     * A string indicating the program used to generate the channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var generator: String? = null,

    /**
     * Specifies a GIF, JPEG or PNG image that can be displayed with the channel.
     * More info [here](https://www.rssboard.org/rss-specification#ltimagegtSubelementOfLtchannelgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var image: Image? = null,

    @JvmField
    @field:JacksonXmlProperty
    var item: List<Item>? = null,

    /**
     * The language the channel is written in.
     * This allows aggregators to group all Italian language sites, for example, on a single page.
     * A list of allowable values for this element, as provided by Netscape, is [here](https://www.rssboard.org/rss-language-codes).
     * You may also use [values defined](http://www.w3.org/TR/REC-html40/struct/dirlang.html#langcodes) by the W3C.
     */
    @JvmField
    @field:JacksonXmlProperty
    var language: String? = null,

    /**
     * The last time the content of the channel changed.
     */
    @JvmField
    @field:JacksonXmlProperty
    var lastBuildDate: String? = null,

    /**
     * Email address for person responsible for editorial content.
     */
    @JvmField
    @field:JacksonXmlProperty
    var managingEditor: String? = null,

    /**
     * The publication date for the content in the channel.
     * For example, the New York Times publishes on a daily basis,
     * the publication date flips once every 24 hours.
     * That's when the pubDate of the channel changes.
     * All date-times in RSS conform to the Date and Time Specification of [RFC 822](http://asg.web.cmu.edu/rfc/rfc822.html),
     * with the exception that the year may be expressed with two characters or four characters (four preferred).
     */
    @JvmField
    @field:JacksonXmlProperty
    var pubDate: String? = null,

    /**
     * The [PICS](http://www.w3.org/PICS/) rating for the channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var rating: String? = null,

    /**
     * A hint for aggregators telling them which days they can skip.
     * This element contains up to seven <day> sub-elements whose value is Monday, Tuesday, Wednesday, Thursday, Friday, Saturday or Sunday.
     * Aggregators may not read the channel during days listed in the <skipDays> element.
     */
    @JvmField
    @field:JacksonXmlProperty
    var skipDays: SkipDays? = null,

    /**
     * A hint for aggregators telling them which hours they can skip.
     * This element contains up to 24 <hour> sub-elements whose value is a number between 0 and 23, representing a time in GMT, when aggregators, if they support the feature, may not read the channel on hours listed in the <skipHours> element.
     * The hour beginning at midnight is hour zero.
     */
    @JvmField
    @field:JacksonXmlProperty
    var skipHours: SkipHours? = null,

    /**
     * Specifies a text input box that can be displayed with the channel.
     * More info [here](https://www.rssboard.org/rss-specification#lttextinputgtSubelementOfLtchannelgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var textInput: TextInput? = null,

    /**
     * ttl stands for time to live.
     * It's a number of minutes that indicates how long a channel can be cached before refreshing from the source.
     * More info [here](https://www.rssboard.org/rss-specification#ltttlgtSubelementOfLtchannelgt).
     */
    @JvmField
    @field:JacksonXmlProperty
    var ttl: String? = null,

    /**
     * Email address for person responsible for technical issues relating to channel.
     */
    @JvmField
    @field:JacksonXmlProperty
    var webMaster: String? = null,
)
