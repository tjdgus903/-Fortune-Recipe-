package org.example.com.fortune.app.domain.user

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

class UserLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userName: String,
    val birthDate: String,      // YYYYMMDD
    val resultTitle: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)