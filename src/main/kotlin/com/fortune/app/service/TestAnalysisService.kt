package org.example.com.fortune.app.service

import com.fortune.app.domain.question.QuestionOption
import com.fortune.app.service.SajuAnalysisService
import com.fortune.app.domain.saju.SajuCommon
import org.example.com.fortune.app.repository.QuestionOptionRepository
import org.example.com.fortune.app.repository.TestResultRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  사용자의 답변(Option)마다 측정 가중치 혹은 점수를 부여
 *  백엔드 엔진은 이를 합산하여 사주 데이터와 믹싱(Mixing)
 */
@Service
class TestAnalysisService(
    private val sajuService: SajuAnalysisService,
    private val optionRepository: QuestionOptionRepository,
    private val resultRepository: TestResultRepository,
) {
    @Transactional(readOnly = true)
    fun calculateFinalResult(optionIds: List<Long>, birthDate: String): Long {

        // 1. 사주 오행 분석 (Base Score)
        val sajuElements = sajuService.analyzeSaju(birthDate)
        val strongestElement = sajuElements.maxByOrNull { it.value }?.key ?: SajuCommon.Element.EARTH

        // 2. 사용자가 선택한 답변 점수 합산 (Answer Score)
        val selectedOptions = optionRepository.findAllById(optionIds)
        val totalTestScore = selectedOptions.sumOf { it.score }

        /**
         * [복잡한 조건문 로직 - 가상 엔진]
         * 예: 사주에 '화(FIRE)'가 가장 강한데, 설문 점수가 80점 이상이면 '폭발적인 요리'
         * 사주에 '수(WATER)'가 가장 강한데, 설문 점수가 40점 미만이면 '차분한 냉면'
         */
        val resultMatchCode = when {
            strongestElement == SajuCommon.Element.FIRE && totalTestScore >= 70 -> "HOT_PASSION"
            strongestElement == SajuCommon.Element.FIRE && totalTestScore < 70 -> "WARM_STORY"
            strongestElement == SajuCommon.Element.WATER && totalTestScore >= 70 -> "DEEP_OCEAN"
            else -> "DEFAULT_BASIC"
        }

        // 3. 매칭된 코드에 해당하는 결과 ID 반환
        val result = resultRepository.findByResultCode(resultMatchCode)
            ?: throw NoSuchElementException("결과 데이터가 없습니다.")

        return result.id!!
    }
}