package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.Application
import com.nobrain.spring.kotlinexample.repository.RssRepository
import org.hamcrest.core.Is
import org.hamcrest.number.OrderingComparison
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class))
@WebAppConfiguration
class RSSParseServiceTest {

    @Autowired
    lateinit var rssParserService: RSSParseService;

    @Autowired
    lateinit var rssRepository: RssRepository;

    val SAMPLE_URL_RSS_2_0 = "http://rss.hankyung.com/new/news_main.xml"
    val SAMPLE_URL_ATOM = "https://www.google.co.kr/alerts/feeds/15417142430691336811/13762181428138831398"
    @Test
    fun testGetRssEntries() {
        val syncFeed = rssParserService.getSyncFeed(SAMPLE_URL_ATOM)
        val rssEntries = rssParserService.getRssEntries(syncFeed)
        Assert.assertThat(rssEntries.size, Is.`is`(OrderingComparison.greaterThanOrEqualTo(0)));
    }
}