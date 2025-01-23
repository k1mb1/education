package me.k1mb.education.config.error

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val errorResponse =
            createErrorResponse(
                status = HttpStatus.BAD_REQUEST,
                message = exception.message,
                path = request.requestURI,
            )
        log.error("MethodArgumentTypeMismatchException occurred: {}", errorResponse)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    private fun createErrorResponse(
        status: HttpStatus,
        message: String?,
        path: String,
    ): ErrorResponse =
        ErrorResponse(
            code = status.value(),
            status = status,
            message = message,
            timestamp = LocalDateTime.now(),
            path = path,
        )
}
