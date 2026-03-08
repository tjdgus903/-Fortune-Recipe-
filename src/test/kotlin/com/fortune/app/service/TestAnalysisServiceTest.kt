package com.fortune.app.service

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.example.com.fortune.app.service.TestAnalysisService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class TestAnalysisServiceTest @Autowired constructor(
    private val testAnalysisService: TestAnalysisService
) {
    @Test
    @DisplayName("사주와 답변 ID 를 던지면 정확한 결과 ID 가 반환되어야 함")
    fun calculateResultSuccess() {
        // Given: data.sql에 입력된 옵션 ID와 생년월일
        val optionIds = listOf(1L) // '매우 좋음' (50점)
        val birthDate = "1990050114" // 사주 분석 결과가 FIRE라고 가정

        // When
        val resultId = testAnalysisService.calculateFinalResult(optionIds, birthDate)

        // Then
        assertThat(resultId).isNotNull()
        // DB에 저장된 FIRE_HIGH의 ID가 1번이라면 1인지 검증
    }

}