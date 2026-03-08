package org.example.com.fortune.app.domain.common.exception

enum class ErrorCode(val status: Int, val message: String) {
    EVENT_NOT_FOUND(404, "존재하지 않는 이벤트입니다."),
    SAJU_ANALYSIS_FAILED(500, "사주 분석 중 오류가 발생했습니다."),
    RESULT_MAPPING_FAILED(404, "매칭되는 운세 결과를 찾을 수 없습니다."),
    INVALID_INPUT_VALUE(400, "잘못된 입력값입니다.")
}