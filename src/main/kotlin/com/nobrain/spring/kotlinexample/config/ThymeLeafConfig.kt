package com.nobrain.spring.kotlinexample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ServletContextTemplateResolver


@Configuration
open class ThymeLeafConfig {
    @Bean
    open fun templateResolver(): ServletContextTemplateResolver {
        val templateResolver: ServletContextTemplateResolver = ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    @Bean
    open fun templateEngine(): SpringTemplateEngine {
        val templateEngine: SpringTemplateEngine = SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    open fun viewResolver(): ViewResolver {
        val viewResolver: ThymeleafViewResolver = ThymeleafViewResolver() ;
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }

}