package com.nobrain.spring.kotlinexample.controller.rss

import com.nobrain.spring.kotlinexample.domain.RssInfo
import com.nobrain.spring.kotlinexample.service.RSSParseService
import com.sun.syndication.io.ParsingFeedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jsuch2362 on 2016. 2. 28..
 */
@RestController
class RssController {

    @Autowired
    lateinit var rssParseService: RSSParseService

    @RequestMapping("/rss")
    fun getRssInfos(): List<RssInfo> = rssParseService.getRssInfos()

    @RequestMapping("/rss", method = arrayOf(RequestMethod.POST))
    fun addRssInfo(@RequestBody rssInfo: RssInfo): RssInfo {
        try {
            return rssParseService.addRssInfo(rssInfo) ?: throw IllegalArgumentException()
        } catch(e: ParsingFeedException) {
            throw IllegalArgumentException()
        }
    }

}