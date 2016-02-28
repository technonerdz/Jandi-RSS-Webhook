package com.nobrain.spring.kotlinexample.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.MalformedURLException

@ControllerAdvice
class RequestExceptionHandler {

    // Why didn't excute...?

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class, MalformedURLException::class)
    fun handleIllegalArgments() = hashMapOf("code" to 40001, "msg" to "IllegalArguments")
}