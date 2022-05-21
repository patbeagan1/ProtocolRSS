# ProtocolRSS
A kotlin implementation of the RSS protocol. Derived from https://www.rssboard.org/rss-specification

## 🛠 Installation

```groovy
repositories {
    mavenLocal() // more coming soon
}

dependencies {
    def versionProtocolRss = "0.4-SNAPSHOT"
    implementation("dev.patbeagan1:protocol-rss:$versionProtocolRss")
}
```

## 📖 Documentation

Docs can be found [here](https://patbeagan1.github.io/ProtocolRSS/index.html)

## 🌈 Demo

|Within your server|Written file|On a user's RSS reader app|
|--|--|--|
|![Screenshot from 2022-05-21 09-09-50](https://user-images.githubusercontent.com/10187351/169655405-6ac3daeb-7145-47f1-9849-4de315bd08f5.png)|![Screenshot from 2022-05-18 23-08-39](https://user-images.githubusercontent.com/10187351/169202503-9d887b6f-eedd-44c4-9826-da479c542485.png)|<img alt="image" src="https://user-images.githubusercontent.com/10187351/169203679-d476fb41-5640-4683-ac18-31fa1a8aee9b.png" width="200px" />|

## 🤔 What is RSS? 

RSS stands for `Really Simple Syndication`. 

It's a way to standardize content distribution, so you can get to the content that you want to see more quickly. Instead of having to visit a website to get information, you can subscribe to a a feed and prefetch content that you'll be able to read later.

You can think of it like an email mailing list, with the exception that you get to request the content, instead of having content pushed to you.

## Why do people use it?

### It only gives you the content you want
- almost no ads
- almost no tracking

### It gives you that content quickly
- content loads instantly, because it is precached
- content can be seen offline, because it is precached

### It's personal
- It's a way to follow a creator or newsletter very directly
- It's an easy way to aggregate content from multiple websites, in one place 

## That sounds cool - why haven't I heard of this before?

RSS has become niche, due to the rise of social media sites. This does not necessarily mean that social media has replaced RSS, because they are different tools. RSS allows you to pull content directly from the source, instead of via a share. It is focused on [blogs](https://wordpress.com/support/feeds/), [news sites](https://blog.feedspot.com/world_news_rss_feeds/) and [podcasts](https://www.thepodcasthost.com/publishing/what-is-an-rss-feed-for-podcasting/). 

## How does this library help me use RSS?

RSS works by reading an XML file that is hosted as part of a website. This library will allow you generate those XML files, or read ones that are already created.

To be more specific, the library implements the RSS protocol as a series of `data class` files, so that you can build your feed in kotlin and serialize it to XML later on.

## I'm sure this already exists somewhere else - why rewrite it?

Most of the tools that I found to generate RSS files were quite old and written in languages which are not commonly used anymore. I wanted to easily generate feed.xml files in kotlin, so I decided to write a generator myself!
