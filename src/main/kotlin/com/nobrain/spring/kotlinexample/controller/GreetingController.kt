package com.nobrain.spring.kotlinexample.controller

import com.nobrain.spring.kotlinexample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by jsuch2362 on 2016. 2. 21..
 */
@Controller
class GreetingController {

    @Autowired
    lateinit var userRepository: UserRepository

    @RequestMapping("/", "/index")
    fun greeting() = "index"
}