package me.k1mb.education.users

fun UserEntity.toDto(): UserDto =
    UserDto(
        id = this.id,
        username = this.username!!,
        email = this.email!!,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )

fun UserDto.toEntity(): UserEntity =
    UserEntity(
        id = this.id,
        username = this.username,
        email = this.email,
    ).apply {
        this.createdAt = this@toEntity.createdAt ?: this.createdAt
        this.updatedAt = this@toEntity.updatedAt ?: this.updatedAt
    }
