package com.fortune.app.service

import com.fortune.app.domain.saju.SajuCommon
import org.example.com.fortune.app.repository.TestResultRepository
import org.springframework.stereotype.Service

/**
 * [사고 모드 핵심] 사주 점수와 설문 점수를 믹싱하는 알고리즘입니다.
 * 인터페이스화하여 나중에 'MBTI 결합형', '투자 성향 결합형' 등으로 확장 가능합니다.
 */
@Service
class ResultMatchingEngine(
    private val resultRepository: TestResultRepository
) {

    fun match(sajuWeights: Map<SajuCommon.Element, Double>, surveyScore: Int): Long {
        // 1. 사주에서 가장 강한 기운(Main Element) 추출
        val mainElement = sajuWeights.maxByOrNull { it.value }?.key ?: SajuCommon.Element.EARTH

        // 2. 사주 기운과 설문 점수의 상호작용 계산
        // 예: FIRE(열정) 기운이 강한데 설문 점수가 낮으면 -> '번아웃 된 불꽃'
        // FIRE(열정) 기운이 강한데 설문 점수가 높으면 -> '활활 타오르는 태양'

        val resultTag = determineTag(mainElement, surveyScore)

        return resultRepository.findByResultCode(resultTag)?.id
            ?: throw IllegalStateException("Matching Result Not Found: $resultTag")
    }

    private fun determineTag(element: SajuCommon.Element, score: Int): String {
        val level = when {
            score >= 80 -> "ELITE"
            score >= 40 -> "NORMAL"
            else -> "LOW"
        }
        return "${element.name}_$level"
    }
}