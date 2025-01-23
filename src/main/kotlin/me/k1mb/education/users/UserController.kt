package me.k1mb.education.users

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["/users"], produces = ["application/json"])
class UserController(
    private val service: UserService,
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: UUID,
    ) = service.get(id)

    @PostMapping
    fun create(
        @RequestBody dto: UserDto,
    ) = service.create(dto)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: UUID,
    ) = service.delete(id)
}
