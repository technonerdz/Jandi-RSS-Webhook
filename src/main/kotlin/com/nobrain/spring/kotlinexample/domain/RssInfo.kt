package com.nobrain.spring.kotlinexample.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class RssInfo(@Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
                   var id: Long = 0,
                   var rssUrl: String = "",
                   var lastGuid: String = "",
                   var name: String = "")