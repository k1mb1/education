package me.k1mb.education.users

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val id: UUID? = null,
    val username: String,
    val email: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
)
