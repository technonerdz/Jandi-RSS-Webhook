package com.nobrain.spring.kotlinexample.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class JandiWebhook(@Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
                        var id: Long = 0,
                        var url: String = "",
                        var description: String = "",
                        var work: Boolean = true)