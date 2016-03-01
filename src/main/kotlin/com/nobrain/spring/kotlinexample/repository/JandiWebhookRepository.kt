package com.nobrain.spring.kotlinexample.repository

import com.nobrain.spring.kotlinexample.domain.JandiWebhook
import org.springframework.data.repository.CrudRepository

/**
 * Created by jsuch2362 on 2016. 3. 1..
 */
interface JandiWebhookRepository : CrudRepository<JandiWebhook, Long>