package com.fortune.app.service

import com.fortune.app.domain.saju.SajuCommon
import org.springframework.stereotype.Service

@Service
class SajuAnalysisService {

    fun analyzeSaju(birthDate : String): Map<SajuCommon.Element, Int> {
        // 1. 문자열 파싱
        val year = birthDate.substring(0, 4).toInt()
        val month = birthDate.substring(4, 6).toInt()
        val day = birthDate.substring(6, 8).toInt()
        val hour = if (birthDate.length > 8) birthDate.substring(8, 10).toInt() else 0

        // 2. 사주 팔자(8글자) 추출
        val pillars = getSajuPillars(year, month, day, hour)

        // 3. 각 글자의 오행 합산
        val counts = mutableMapOf<SajuCommon.Element, Int>().apply{
            SajuCommon.Element.values().forEach { put(it, 0) }
        }

        pillars.forEach { stalkOrBranch ->
            val element = when (stalkOrBranch) {
                is SajuCommon.HeavenlyStalk -> stalkOrBranch.element
                is SajuCommon.EarthlyBranch -> stalkOrBranch.element
                else -> throw IllegalArgumentException("Invalid Pillar")
            }
            element?.let { counts[it] = counts[it]!! + 1 }
        }

        return counts
    }

    private fun getSajuPillars(y: Int, m: Int, d: Int, h: Int): List<Any> {
        // TODO: 만세력 라이브러리 연동 (예: '갑인', '병사' 등 추출)
        return listOf(SajuCommon.HeavenlyStalk.GAP, SajuCommon.EarthlyBranch.IN, SajuCommon.HeavenlyStalk.BYEONG, SajuCommon.EarthlyBranch.SA)
    }
}