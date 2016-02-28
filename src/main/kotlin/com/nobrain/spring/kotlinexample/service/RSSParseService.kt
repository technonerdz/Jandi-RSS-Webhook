package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.domain.RssEntry
import com.nobrain.spring.kotlinexample.domain.RssInfo
import com.nobrain.spring.kotlinexample.repository.RssRepository
import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.feed.synd.SyndFeed
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URL
import java.util.*

@Service
class RSSParseService {
    @Autowired
    lateinit var rssRepository: RssRepository

    fun getRssEntries(feed: SyndFeed?): List<RssEntry> {

        val entries: List<RssEntry> = feed?.entries?.map { it ->
            it as SyndEntry
        }?.map { it ->
            RssEntry(it.link, it.title, it.description.value, it.uri)
        } ?: ArrayList<RssEntry>()

        return entries;
    }

    fun getSyncFeed(url: String): SyndFeed? {
        var url: URL = URL(url);
        var syndFeed: SyndFeedInput = SyndFeedInput();

        var feed: SyndFeed? = syndFeed.build(XmlReader(url));
        return feed
    }

    fun getLastRssUri(url: String): String {
        val findOne: RssInfo = rssRepository.findOne(url)
        return findOne.lastGuid
    }

    fun updateLastRssUri(url: String, uri: String): RssInfo {
        var rssInfo: RssInfo? = rssRepository.findOne(url)

        rssInfo?.lastGuid = uri

        return rssRepository.save(rssInfo ?: RssInfo(url, uri))
    }

    fun getLastRssEntries(entries: List<RssEntry>, lastGuid: String): List<RssEntry> {
        return entries.takeWhile { it -> it.guid != lastGuid }
    }

    fun getRssInfos(): List<RssInfo> = rssRepository.findAll().toList()

    fun addRssInfo(rssInfo: RssInfo): RssInfo? {
        val syncFeed = getSyncFeed(rssInfo.rssUrl)

        rssInfo.name = syncFeed?.title ?: ""
        rssInfo.lastGuid = getRssEntries(syncFeed).firstOrNull()?.guid ?: ""
        return rssRepository.save(rssInfo)

    }

    fun removeRssInfo(rssInfo: RssInfo) {
        rssRepository.delete(rssInfo.rssUrl)
    }
}