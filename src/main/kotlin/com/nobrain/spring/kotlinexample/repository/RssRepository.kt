package com.nobrain.spring.kotlinexample.repository

import com.nobrain.spring.kotlinexample.domain.RssInfo
import org.springframework.data.repository.CrudRepository

interface RssRepository : CrudRepository<RssInfo, String>