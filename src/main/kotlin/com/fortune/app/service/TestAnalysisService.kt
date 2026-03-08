package org.example.com.fortune.app.service

import com.fortune.app.domain.question.QuestionOption
import com.fortune.app.service.SajuAnalysisService
import com.fortune.app.domain.saju.SajuCommon
import org.example.com.fortune.app.domain.common.exception.BusinessException
import org.example.com.fortune.app.domain.common.exception.ErrorCode
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
    private val resultRepository: TestResultRepository
) {

    /**
     * 전체 실행 흐름:
     * 1. 사주 분석 -> 2. 설문 합산 -> 3. 매칭 코드 생성 -> 4. 결과 조회
     */
    @Transactional(readOnly = true)
    fun calculateFinalResult(optionIds: List<Long>, birthDate: String): Long {
        // 1. 사주 분석 로직 호출
        val sajuMap = sajuService.analyzeSaju(birthDate)

        // 2. 선택한 답변들의 점수 합산
        val options = optionRepository.findAllById(optionIds)
        if (options.isEmpty()) throw BusinessException(ErrorCode.INVALID_INPUT_VALUE)
        val totalScore = options.sumOf { it.score }

        // 3. 매칭 코드 생성 (여기가 핵심 질문 지점입니다)
        val matchCode = generateMatchCode(sajuMap, totalScore)

        // 4. 생성된 코드로 DB에서 최종 결과(이미지, 텍스트 등) 조회
        val result = resultRepository.findByResultCode(matchCode)
            ?: throw BusinessException(ErrorCode.RESULT_MAPPING_FAILED)

        return result.id!!
    }

    /**
     * [generateMatchCode 내부 상세 로직]
     * * 순차적 동작 흐름:
     * 1. 사주 결과 중 가장 수치가 높은 '대표 오행'을 찾습니다.
     * 2. 설문 총점을 기준에 따라 등급(HIGH, MID, LOW)으로 나눕니다.
     * 3. 두 문자열을 언더바(_)로 연결하여 하나의 코드를 완성합니다.
     */
    private fun generateMatchCode(sajuMap: Map<SajuCommon.Element, Int>, totalScore: Int): String {
        // 1. 대표 오행 추출 (값이 가장 큰 key를 찾음)
        val strongestElement = sajuMap.maxByOrNull { it.value }?.key ?: SajuCommon.Element.EARTH

        // 2. 설문 점수 등급화 (기업용 로직에서는 이 경계값을 설정 파일로 관리하기도 합니다)
        val scoreGrade = when {
            totalScore >= 80 -> "HIGH"
            totalScore >= 40 -> "MID"
            else -> "LOW"
        }

        // 3. 최종 코드 조합 (예: "FIRE" + "_" + "HIGH" = "FIRE_HIGH")
        return "${strongestElement.name}_$scoreGrade"
    }
}