package com.nobrain.spring.kotlinexample.controller.webhook

import com.nobrain.spring.kotlinexample.domain.JandiWebhook
import com.nobrain.spring.kotlinexample.repository.JandiWebhookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class WebhookController {

    @Autowired
    lateinit var jandiWebhookRepository: JandiWebhookRepository

    @RequestMapping("/webhook")
    fun getWebhooks() = jandiWebhookRepository.findAll().toList()

    @RequestMapping("/webhook", method = arrayOf(RequestMethod.POST))
    fun addWebhook(@RequestBody jandiWebhook: JandiWebhook) = jandiWebhookRepository.save(jandiWebhook)

    @RequestMapping("/webhook/{webhookId}/work", method = arrayOf(RequestMethod.PUT))
    fun updateWebhookWorkStatus(@PathVariable webhookId: Long,
                                @RequestBody jandiWebhook: JandiWebhook): JandiWebhook {
        val savedWebhoook = jandiWebhookRepository.findOne(webhookId)
        savedWebhoook.work = jandiWebhook.work;
        return jandiWebhookRepository.save(savedWebhoook)
    }
}


