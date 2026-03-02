package org.example.com.fortune.app.domain.result

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TestResult(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val title: String,          // 결과 제목 (ex: 매콤한 마라탕 타입)
    @Column(columnDefinition = "TEXT")
    val description: String,    // 상세 설명
    val imageUrl: String,       // 결과 이미지 경로(S3 또는 로컬)
    val resultCode: String,     // 로직에서 매칭할 코드(ex: FILE_HIGH_SCORE)
)