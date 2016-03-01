package com.nobrain.spring.kotlinexample.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by jsuch2362 on 2016. 2. 28..
 */
@Configuration
open class RssWebMvcConfig : WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry?.addResourceHandler("/resources/**")
                ?.addResourceLocations("/resources/")
    }
}