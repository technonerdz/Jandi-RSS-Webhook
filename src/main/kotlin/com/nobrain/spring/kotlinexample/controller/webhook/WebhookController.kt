package com.nobrain.spring.kotlinexample.controller.webhook

import com.nobrain.spring.kotlinexample.domain.JandiWebhook
import com.nobrain.spring.kotlinexample.repository.JandiWebhookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookController {

    @Autowired
    lateinit var jandiWebhookRepository: JandiWebhookRepository

    @RequestMapping("/webhook")
    fun getWebhooks() = jandiWebhookRepository.findAll().toList()

    @RequestMapping("/webhook", method = arrayOf(RequestMethod.POST))
    fun addWebhook(@RequestBody jandiWebhook: JandiWebhook) = jandiWebhookRepository.save(jandiWebhook)
}


