package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.Application
import com.nobrain.spring.kotlinexample.repository.RssRepository
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

}