package com.nobrain.spring.kotlinexample.service

import com.nobrain.spring.kotlinexample.domain.RssInfo
import com.nobrain.spring.kotlinexample.repository.RssRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by jsuch2362 on 2016. 3. 1..
 */
@Service
class RssService {

    @Autowired
    lateinit var rssRepository: RssRepository

    fun removeRssInfo(rssInfo: RssInfo) {
        val findOne = rssRepository.findOne(rssInfo.rssUrl)
        rssRepository.delete(findOne?.id)
    }

    fun getRssInfo(rssId: Long) = rssRepository.findOne(rssId)

    fun deleteRssInfo(rssId: Long): RssInfo? {
        val rssInfo: RssInfo? = rssRepository.findOne(rssId)
        rssRepository.delete(rssInfo?.id)
        return rssInfo
    }

    fun getRssInfos(): List<RssInfo> = rssRepository.findAll().toList()

    fun updateWorkStatus(rssId: Long, rssInfo: RssInfo): RssInfo? {

        val savedRssInfo: RssInfo? = rssRepository.findOne(rssId)
        savedRssInfo?.work = rssInfo.work
        return rssRepository.save(savedRssInfo)
    }
}