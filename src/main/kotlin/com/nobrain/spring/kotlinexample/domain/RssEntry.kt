package com.nobrain.spring.kotlinexample.domain

data class RssEntry(val link: String,
                    val title: String,
                    val description: String = "",
                    val guid: String)