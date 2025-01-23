package me.k1mb.education.utils

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
class Timestamped(
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    var createdAt: LocalDateTime? = null,
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,
)
