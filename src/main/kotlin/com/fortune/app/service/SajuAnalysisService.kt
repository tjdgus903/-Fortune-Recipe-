package org.example.com.fortune.app.service

import org.example.com.fortune.app.domain.saju.SajuCommon
import org.springframework.stereotype.Service

@Service
class SajuAnalysisService {

    fun analyzeSaju(year: Int, month: Int, day: Int, hour: Int): Map<SajuCommon.Element, Int> {
        // 1. 만세력 라이브러리를 사용해 8글자(사주팔자) 추출
        // 예: [GAP, IN, BYEONG, SA, ...]
        val pillars = getSajuPillars(year, month, day, hour)

        // 2. 각 글자의 오행 합산
        val elementCounts = mutableMapOf<SajuCommon.Element, Int>()
        SajuCommon.Element.values().forEach { elementCounts[it] = 0 }

        pillars.forEach { stalkOrBranch ->
            val element = when (stalkOrBranch) {
                is SajuCommon.HeavenlyStalk -> stalkOrBranch.element
                is SajuCommon.EarthlyBranch -> stalkOrBranch.element
                else -> throw IllegalArgumentException("Invalid Pillar")
            }
            elementCounts[element] = elementCounts[element]!! + 1
        }

        return elementCounts
    }

    private fun getSajuPillars(y: Int, m: Int, d: Int, h: Int): List<Any> {
        // TODO: 만세력 라이브러리 연동 (예: '갑인', '병사' 등 추출)
        // 현재는 테스트용 더미 데이터 반환
        return listOf(SajuCommon.HeavenlyStalk.GAP, SajuCommon.EarthlyBranch.IN, SajuCommon.HeavenlyStalk.BYEONG, SajuCommon.EarthlyBranch.SA)
    }
}