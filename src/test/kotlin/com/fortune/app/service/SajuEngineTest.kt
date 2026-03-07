package com.fortune.app.service

import com.fortune.app.domain.saju.SajuCommon
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SajuEngineTest {

    private val sajuEngine = SajuEngine()

    @Test
    @DisplayName("월지의 가중치가 정상적으로 반영되어 정규화되는지 확인")
    fun weightNormalizationTest() {
        // Given: 월지에 FIRE, 나머지에 WATER가 있는 극단적 상황 설정
        val pillars = listOf(
            SajuPillar(SajuCommon.Element.FIRE, Position.MONTH_BRANCH), // Weight 3.0
            SajuPillar(SajuCommon.Element.WATER, Position.YEAR_STEM)    // Weight 1.0
        )

        // When
        val result = sajuEngine.calculateElementWeights(pillars)

        // Then: FIRE는 3/4(75%), WATER는 1/4(25%)여야 함
        assertThat(result[SajuCommon.Element.FIRE]).isEqualTo(75.0)
        assertThat(result[SajuCommon.Element.WATER]).isEqualTo(25.0)
    }
}