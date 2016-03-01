package com.nobrain.spring.kotlinexample.controller.rss

import com.nobrain.spring.kotlinexample.domain.JandiWebhookData
import com.nobrain.spring.kotlinexample.domain.RssEntry
import com.nobrain.spring.kotlinexample.domain.RssInfo
import com.nobrain.spring.kotlinexample.service.JandiWebHookService
import com.nobrain.spring.kotlinexample.service.RSSParseService
import com.nobrain.spring.kotlinexample.service.RssService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by jsuch2362 on 2016. 2. 28..
 */
@RestController
class RssController {

    @Autowired
    lateinit var rssParseService: RSSParseService

    @Autowired
    lateinit var jandiWebhookService: JandiWebHookService

    @Autowired
    lateinit var rssService: RssService

    @RequestMapping("/rss")
    fun getRssInfos(): List<RssInfo> = rssService.getRssInfos()

    @RequestMapping("/rss/{rssId}")
    fun getRssInfo(@PathVariable rssId: Long) = rssService.getRssInfo(rssId)

    @RequestMapping("/rss/{rssId}", method = arrayOf(RequestMethod.DELETE))
    fun deleteRssInfo(@PathVariable rssId: Long) = rssService.deleteRssInfo(rssId)

    @RequestMapping("/rss", method = arrayOf(RequestMethod.POST))
    fun addRssInfo(@RequestBody rssInfo: RssInfo): RssInfo? {
        return rssParseService.createRssInfo(rssInfo)
    }

    @RequestMapping("/rss/harvest")
    fun pushAllRSSFeed() {
        val allRssFeed: List<Pair<RssInfo, List<RssEntry>>> = rssParseService.getAllRSSFeed()
        val jandiWebhookData: List<JandiWebhookData> = jandiWebhookService.createJandiWebhookDatas(allRssFeed)
        jandiWebhookService.pushWebhook(jandiWebhookData);
        rssParseService.updateLastRssUri(allRssFeed);
    }

    @RequestMapping("/rss/{rssId}/harvest")
    fun pushRSSFeed(@PathVariable rssId: Long) {
        val rssFeed: Pair<RssInfo, List<RssEntry>> = rssParseService.getRSSFeed(rssId)
        val jandiWebhookData = jandiWebhookService.createJandiWebhookDatas(arrayListOf(rssFeed))
        jandiWebhookService.pushWebhook(jandiWebhookData);
        rssParseService.updateLastRssUri(arrayListOf(rssFeed));
    }

}