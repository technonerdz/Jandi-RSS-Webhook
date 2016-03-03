package com.nobrain.spring.kotlinexample.domain

import java.util.*

/**
 * Created by jsuch2362 on 2016. 3. 1..
 */

data class JandiWebhookData(
        var body: String = "",
        var connectInfo: List<JandiWebhookConnectInfo> = ArrayList()) {
    val connectColor: String = "#FAC11B"
}

data class JandiWebhookConnectInfo(
        val title: String,
        val description: String) {
    companion object {
        fun create(rssEntry: RssEntry): JandiWebhookConnectInfo {
            val title = "[${rssEntry.title}](${rssEntry.link})"
            val desciption = rssEntry.description
            return JandiWebhookConnectInfo(title, desciption)
        }
    }
}