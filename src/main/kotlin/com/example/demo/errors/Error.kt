package com.example.demo.errors

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.dao.InvalidDataAccessResourceUsageException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@RestControllerAdvice
class Error: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(PutBadRequest::class)])
    fun handlePutBadRequest(error: PutBadRequest, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails( Date(), "Validation Failed", error.message), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [(NotOrderedException::class)])
    fun handleNotOrdered(error: NotOrderedException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails( Date(), "Validation Failed", error.message), HttpStatus.EXPECTATION_FAILED)
    }

    @ExceptionHandler(value = [(AlreadyReviewedException::class)])
    fun handleAlreadyReviewed(error: AlreadyReviewedException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails( Date(), "Validation Failed", error.message), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [(UserNotFoundException::class)])
    fun handleUserNotFound(error: UserNotFoundException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails( Date(), "Validation Failed", error.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(ProductNotFoundException::class)])
    fun handleProductNotFound(error: ProductNotFoundException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails( Date(), "Validation Failed", error.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(CustomConversionException::class)])
    fun handleCustomConversionException(error: CustomConversionException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails(Date(), "There was an error", error.message), HttpStatus.I_AM_A_TEAPOT)
    }

    @ExceptionHandler(value = [(InvalidDataAccessResourceUsageException::class)])
    fun handleInvalidDataAccessException(error: InvalidDataAccessResourceUsageException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails(Date(), "Not Found", error.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(EmptyResultDataAccessException::class)])
    fun handleEmptyResultException(error: EmptyResultDataAccessException, request: WebRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails(Date(), "Not Found"), HttpStatus.NOT_FOUND)
    }
}

class PutBadRequest(override val message: String?): Exception(message)

class NotOrderedException(override val message: String?): Exception(message)

class AlreadyReviewedException(override val message: String?): Exception(message)

class UserNotFoundException(override val message: String?): Exception(message)

class ProductNotFoundException(override val message: String?): Exception(message)

class CustomConversionException(override  val message: String?): Exception(message)

data class ErrorDetails(val time: Date, val message: String, val details: String? = "")