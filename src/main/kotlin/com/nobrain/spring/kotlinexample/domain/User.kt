package com.nobrain.spring.kotlinexample.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long = 0,
                var name: String = "")