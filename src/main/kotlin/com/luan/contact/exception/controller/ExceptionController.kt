package com.luan.contact.exception.controller

import com.luan.contact.exception.model.Error
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun genericHandler(exception: Exception): Error {
        return Error(
            listOf(
                exception.message ?: "Erro desconhecido"
            )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationHandler(exception: MethodArgumentNotValidException): Error {
        val messages: MutableList<String> = mutableListOf();

        exception.bindingResult.allErrors.forEach { error -> messages.add(error.defaultMessage ?: "Erro desconhecido") }

        return Error(messages);
    }
}