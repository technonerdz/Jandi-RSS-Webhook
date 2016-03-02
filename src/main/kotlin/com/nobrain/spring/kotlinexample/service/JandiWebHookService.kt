package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.domain.*
import com.nobrain.spring.kotlinexample.repository.JandiWebhookRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by jsuch2362 on 2016. 3. 1..
 */

@Service
class JandiWebHookService {

    @Autowired
    lateinit var jandiWebhookRepository: JandiWebhookRepository

    fun createJandiWebhookDatas(allRssFeed: List<Pair<RssInfo, List<RssEntry>>>): List<JandiWebhookData> {


        val hookData: List<JandiWebhookData> = allRssFeed.map { it ->
            val connectInfos: List<JandiWebhookConnectInfo> = it.second.map { it ->
                JandiWebhookConnectInfo.create(it)
            }.toList()
            JandiWebhookData(body = "${it.first.name}'s RSS", connectInfo = connectInfos)
        }

        return hookData
    }

    fun pushWebhook(jandiWebhookData: List<JandiWebhookData>) {
        val webhook: List<JandiWebhook> = jandiWebhookRepository.findAll().filter { it.work }.toList()
        webhook.forEach { it ->
            val webhookUrl = it.url
            jandiWebhookData
                    .filter { !it.connectInfo.isEmpty() }
                    .forEach { that ->
                        sendWebhook(webhookUrl, that)
                    }
        }
    }

    companion object {
        fun sendWebhook(url: String, jandiWebhookData: JandiWebhookData): Int {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val split = url.split("/")
            val room = split[split.size - 2]
            val key = split[split.size - 1]

            val execute: Response<Map<Object, Object>>? = Retrofit.Builder()
                    .baseUrl("https://wh.jandi.com/")
                    .client(client)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build()
                    .create(WebhookApi::class.java)
                    .sendWebhook(room, key, jandiWebhookData)
                    .execute()
            return execute?.code() ?: 400
        }
    }
}


interface WebhookApi {

    @Headers(
            "Accept: application/vnd.tosslab.jandi-v2+json",
            "Content-Type: application/json"
    )
    @POST("/connect-api/webhook/{room}/{key}")
    fun sendWebhook(@Path("room") room: String, @Path("key") key: String, @Body connectInfo: JandiWebhookData): Call<Map<Object, Object>>
}