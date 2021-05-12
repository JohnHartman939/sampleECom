package com.example.demo.errors

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class Error: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(PutBadRequest::class)])
    fun handlePutBadRequest(error: PutBadRequest, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails( Date(), "Validation Failed", error.message), HttpStatus.CONFLICT)
    }
}

class PutBadRequest(override val message: String?): Exception(message)

data class ErrorDetails(val time: Date, val message: String, val details: String?)