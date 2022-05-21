package dev.patbeagan.protocolrss.core

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * `<cloud>` is an optional sub-element of `<channel>`.
 *
 * It specifies a web service that supports the rssCloud interface which can be implemented in HTTP-POST, XML-RPC or SOAP 1.1.
 *
 * Its purpose is to allow processes to register with a cloud to be notified of updates to the channel, implementing a lightweight publish-subscribe protocol for RSS feeds.
 *
 *```
 * <cloud
 *   domain="rpc.sys.com"
 *   port="80"
 *   path="/RPC2"
 *   registerProcedure="myCloud.rssPleaseNotify"
 *   protocol="xml-rpc" />
 *```
 *
 * In this example, to request notification on the channel it appears in, you would send an XML-RPC message to rpc.sys.com on port 80, with a path of /RPC2. The procedure to call is myCloud.rssPleaseNotify.
 *
 *A full explanation of this element and the rssCloud interface is [here](https://www.rssboard.org/rsscloud-interface).
 */
data class Cloud(
    /**
     * A list of URLs of RSS documents that the client seeks to monitor.
     * Multiple cloud tags are allowed in a channel.
     */
    @JvmField @field:JacksonXmlProperty(isAttribute = true) var domain: String,

    /**
     * The client's TCP port
     */
    @JvmField @field:JacksonXmlProperty(isAttribute = true) var port: Int,

    /**
     * The client's remote procedure call path
     */
    @JvmField @field:JacksonXmlProperty(isAttribute = true) var path: String,

    /**
     * The name of the remote procedure the cloud should call on the client upon an update
     */
    @JvmField @field:JacksonXmlProperty(isAttribute = true) var registerProcedure: String,

    /**
     * The string "xml-rpc" if the client employs XML-RPC, "soap" for SOAP and "http-post" for REST.
     */
    @JvmField @field:JacksonXmlProperty(isAttribute = true) var protocol: Protocol,
) {
    enum class Protocol {
        @JsonProperty("xml-rpc")
        XML_RPC,

        @JsonProperty("soap")
        SOAP,

        @JsonProperty("http-post")
        REST
    }
}
