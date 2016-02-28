package com.nobrain.spring.kotlinexample.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class RssInfo(@Id var rssUrl: String = "",
                   var lastGuid: String = "",
                   var name: String = "")