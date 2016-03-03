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

    internal fun getRssEntries(feed: SyndFeed?): List<RssEntry> {

        val entries: List<RssEntry> = feed?.entries?.map { it ->
            it as SyndEntry
        }?.map { it ->
            var description = if (it.description != null) {
                it.description.value
            } else {
                ""
            }
            RssEntry(it.link, it.title, description, it.uri)
        } ?: ArrayList<RssEntry>()

        return entries;
    }

    internal fun getSyncFeed(url: String): SyndFeed? {
        var url: URL = URL(url);
        var syndFeed: SyndFeedInput = SyndFeedInput();

        var feed: SyndFeed? = syndFeed.build(XmlReader(url));
        return feed
    }

    private fun getLastRssUri(url: String): String {
        val findOne: RssInfo? = rssRepository.findOne(url)
        return findOne?.lastGuid ?: ""
    }

    fun updateLastRssUri(url: String, uri: String): RssInfo {
        var rssInfo: RssInfo? = rssRepository.findOne(url)

        rssInfo?.lastGuid = uri

        return rssRepository.save(rssInfo ?: RssInfo(0, url, uri))
    }

    fun updateLastRssUri(rssEntries: List<Pair<RssInfo, List<RssEntry>>>) {

        rssEntries.forEach { it ->
            it.first.lastGuid = it.second.firstOrNull()?.guid ?: it.first.lastGuid
            rssRepository.save(it.first)
        }

    }

    private fun getLastRssEntries(entries: List<RssEntry>, lastGuid: String): List<RssEntry> {
        return entries.takeWhile { it -> it.guid != lastGuid }
    }

    fun createRssInfo(rssInfo: RssInfo): RssInfo? {
        val syncFeed = getSyncFeed(rssInfo.rssUrl)
        if (syncFeed != null ) {
            rssInfo.name = syncFeed.title ?: ""
            return rssRepository.save(rssInfo)
        } else {
            return rssInfo
        }
    }

    fun getAllRSSFeed(): List<Pair<RssInfo, List<RssEntry>>> {
        val list: List<Pair<RssInfo, List<RssEntry>>> = rssRepository.findAll()
                .filter { it.work }
                .map { it ->
                    val syncFeed = getSyncFeed(it.rssUrl)
                    val lastRssEntries = getLastRssEntries(getRssEntries(syncFeed), it.lastGuid)
                    Pair<RssInfo, List<RssEntry>>(it, lastRssEntries)
                }
        return list;
    }

    fun getRSSFeed(rssId: Long): Pair<RssInfo, List<RssEntry>> {
        val rssInfo: RssInfo? = rssRepository.findOne(rssId)
        if (rssInfo != null) {
            return Pair(rssInfo, getLastRssEntries(getRssEntries(getSyncFeed(rssInfo.rssUrl)), rssInfo.lastGuid))
        } else {
            return Pair<RssInfo, List<RssEntry>>(RssInfo(), ArrayList<RssEntry>())
        }
    }
}