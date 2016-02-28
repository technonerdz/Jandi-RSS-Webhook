package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.Application
import com.nobrain.spring.kotlinexample.domain.RssEntry
import com.nobrain.spring.kotlinexample.domain.RssInfo
import com.nobrain.spring.kotlinexample.repository.RssRepository
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNull.notNullValue
import org.hamcrest.core.IsNull.nullValue
import org.junit.Assert.assertThat
import org.junit.Assert.fail
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

    val SAMPLE_URL = "http://rss.hankyung.com/new/news_main.xml"

    @Test
    fun testGetLastRssEntries() {
        val syncFeed = rssParserService.getSyncFeed(SAMPLE_URL)
        val rssEntries = rssParserService.getRssEntries(syncFeed)
        var lastRssEntries = rssParserService.getLastRssEntries(rssEntries, rssEntries.get(0).guid)
        assertThat(lastRssEntries.size, `is`(0))

        lastRssEntries = rssParserService.getLastRssEntries(rssEntries, rssEntries.get(1).guid)
        assertThat(lastRssEntries.size, `is`(1))

        try {
            val rssEntries1: List<RssEntry> = rssParserService.getRssEntries(rssParserService.getSyncFeed("http://www.jandi.com"))
            fail("It cannot be success!!!")
        } catch(e: Exception) {
        }
    }

    @Test
    fun testUpdateLastUri() {
        val updateLastRssUri = rssParserService.updateLastRssUri(SAMPLE_URL, "1")

        assertThat(updateLastRssUri, `is`(notNullValue()));
        assertThat(updateLastRssUri.rssUrl, `is`(equalTo(SAMPLE_URL)));
        assertThat(updateLastRssUri.lastGuid, `is`(equalTo("1")));

        val rssInfo = rssRepository.findOne(SAMPLE_URL)

        assertThat(updateLastRssUri.rssUrl, `is`(equalTo(rssInfo.rssUrl)))
        assertThat(updateLastRssUri.lastGuid, `is`(equalTo(rssInfo.lastGuid)))
    }

    @Test
    fun testGetLastRssUri() {

        rssRepository.save(RssInfo(SAMPLE_URL, "10"))
        val lastRssUri = rssParserService.getLastRssUri(SAMPLE_URL)

        assertThat(lastRssUri, `is`(equalTo("10")))


    }

    @Test
    fun testRemoveRssInfo() {
        rssRepository.save(RssInfo(rssUrl = SAMPLE_URL))

        rssParserService.removeRssInfo(RssInfo(rssUrl = SAMPLE_URL))

        val rssInfo = rssRepository.findOne(SAMPLE_URL)
        assertThat(rssInfo, `is`(nullValue()))

    }
}