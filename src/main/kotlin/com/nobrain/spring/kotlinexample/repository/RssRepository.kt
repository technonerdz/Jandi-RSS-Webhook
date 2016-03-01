package com.nobrain.spring.kotlinexample.repository

import com.nobrain.spring.kotlinexample.domain.RssInfo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface RssRepository : CrudRepository<RssInfo, Long> {
    @Query("SELECT r FROM RssInfo r WHERE r.rssUrl = :rssUrl")
    fun findOne(@Param("rssUrl") rssUrl: String): RssInfo?
}