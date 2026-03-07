package com.fortune.app.service

import com.fortune.app.domain.saju.SajuCommon
import org.springframework.stereotype.Component

/**
 * [Senior 포인트] 단순 개수 세기가 아닌, 위치별 가중치를 적용한 오행 분석기입니다.
 * 사주 명리학에서 '월지'와 '일지'의 영향력을 프로그래밍적으로 수치화했습니다.
 */
@Component
class SajuEngine {

    fun calculateElementWeights(pillars: List<SajuPillar>): Map<SajuCommon.Element, Double> {
        val weights = mutableMapOf<SajuCommon.Element, Double>().apply {
            SajuCommon.Element.values().forEach { put(it, 0.0) }
        }

        pillars.forEach { pillar ->
            val weight = when (pillar.position) {
                Position.MONTH_BRANCH -> 3.0  // 월지: 가장 강력한 영향력
                Position.DAY_STEM -> 2.0      // 일간: 본인을 상징
                else -> 1.0                   // 나머지 1.0
            }
            weights[pillar.element] = weights[pillar.element]!! + weight
        }

        // 전체 합을 100%로 정규화(Normalization)하여 결과 도출의 일관성 확보
        val total = weights.values.sum()
        return weights.mapValues { (_, value) -> (value / total) * 100 }
    }
}

data class SajuPillar(val element: SajuCommon.Element, val position: Position)
enum class Position { YEAR_STEM, YEAR_BRANCH, MONTH_STEM, MONTH_BRANCH, DAY_STEM, DAY_BRANCH, HOUR_STEM, HOUR_BRANCH }