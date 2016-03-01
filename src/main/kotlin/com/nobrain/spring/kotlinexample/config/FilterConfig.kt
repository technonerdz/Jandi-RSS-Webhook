package com.nobrain.spring.kotlinexample.config

import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter

/**
 * Created by jsuch2362 on 2016. 3. 1..
 */
@Configuration
open class FilterConfig {

    @Bean
    open fun filterRegistrationBean(): FilterRegistrationBean {
        val registrationBean: FilterRegistrationBean = FilterRegistrationBean()
        val characterEncodingFilter: CharacterEncodingFilter = CharacterEncodingFilter()
        characterEncodingFilter.setEncoding("UTF-8")
        registrationBean.setFilter(characterEncodingFilter)
        registrationBean.addUrlPatterns("/*")
        return registrationBean;
    }
}