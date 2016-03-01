package com.nobrain.spring.kotlinexample.controller

import com.nobrain.spring.kotlinexample.domain.JandiWebhook
import com.nobrain.spring.kotlinexample.domain.RssInfo
import com.nobrain.spring.kotlinexample.repository.JandiWebhookRepository
import com.nobrain.spring.kotlinexample.repository.RssRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
class GreetingController {

    @Autowired
    lateinit var rssRepository: RssRepository
    @Autowired
    lateinit var jandiWebhookRepository: JandiWebhookRepository

    @RequestMapping("/", "/index")
    fun greeting(model: Model): String {
        val rssInfos: List<RssInfo> = rssRepository.findAll()?.toList() ?: ArrayList()

        if (rssInfos.isEmpty()) {
            model.addAttribute("length", 0)
            model.addAttribute("datas", ArrayList<RssInfo>())
        } else {
            model.addAttribute("length", rssInfos.size)
            model.addAttribute("datas", rssInfos)
        }

        val webhooks: List<JandiWebhook> = jandiWebhookRepository.findAll()?.toList() ?: ArrayList()

        if (!webhooks.isEmpty()) {
            model.addAttribute("webhookLength", webhooks.size)
            model.addAttribute("webhookDatas", webhooks)
        } else {
            model.addAttribute("webhookLength", 0)
        }

        return "index"
    }
}