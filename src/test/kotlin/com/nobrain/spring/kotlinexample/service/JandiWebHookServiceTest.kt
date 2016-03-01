package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.Application
import com.nobrain.spring.kotlinexample.domain.JandiWebhookConnectInfo
import com.nobrain.spring.kotlinexample.domain.JandiWebhookData
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class))
@WebAppConfiguration
class JandiWebHookServiceTest {

    @Test
    fun testSendWebhook() {
        println()
        val sendWebhook = JandiWebHookService.sendWebhook("https://wh.jandi.com/connect-api/webhook/279/435df354f02086d5b7a9b034d404c2df",
                JandiWebhookData(body = "test", connectInfo = arrayListOf(JandiWebhookConnectInfo("title1", "desc1"))))

    }
}