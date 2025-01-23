package me.k1mb.education.users

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val repository: UserRepository,
) {
    fun create(dto: UserDto): UserDto = repository.save(dto.toEntity()).toDto()

    fun get(id: UUID): UserDto = repository.findById(id).orElseThrow().toDto()

    fun getAll(): List<UserDto> = repository.findAll().map { it.toDto() }

    fun delete(id: UUID) = repository.deleteById(id)
}
