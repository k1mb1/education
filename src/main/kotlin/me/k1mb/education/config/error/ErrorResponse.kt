package me.k1mb.education.config.error

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    val code: Int,
    val status: HttpStatus,
    val message: String?,
    val timestamp: LocalDateTime,
    val path: String,
)
