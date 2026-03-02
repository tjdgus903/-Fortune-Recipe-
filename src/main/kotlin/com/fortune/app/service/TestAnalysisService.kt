package org.example.com.fortune.app.service

import org.example.com.fortune.app.domain.saju.SajuCommon
import org.springframework.stereotype.Service

/**
 *  사용자의 답변(Option)마다 측정 가중치 혹은 점수를 부여
 *  백엔드 엔진은 이를 합산하여 사주 데이터와 믹싱(Mixing)
 */
@Service
class TestAnalysisService(
    private val sajuService: SajuAnalysisService,
) {
    fun calculateFinalResult(userAnswers: List<Long>, birthDate: String): Long {
        // 1. 사주 오행 분석 (이전 단계에서 만든 서비스 호출)
        // [사고 모드 권장]: 만세력 계산 및 오행 비중 산출은 연산량이 많으므로 로직 최적화가 필요합니다.
        val sajuElements = sajuService.analyzeSaju(birthDate)

        // 2. 답변 기반 점수 합산
        // 실제 운영 시에는 DB에서 각 Option의 score와 targetElement(가중치를 줄 오행)를 가져옵니다.
        var fireScore = 0
        val waterScore = 0

        // 답변 루트

        // 3. 최종 결과 매핑 (사주 기운 + 답변 점수)
        // 예: 사주에 '불(FIRE)'이 부족한데 답변에서 '열정'을 선택했다면 특정 결과ID 반환
        return determineResultId(sajuElements, fireScore, waterScore)
    }

    private fun determineResultId(elements: Map<SajuCommon.Element, Int>, fire: Int, water: Int): Long {

        // 조건문 수행

        return 1L   // 임시 결과 ID
    }
}