package org.example.com.fortune.app.domain.saju

class SajuCommon {
    enum class Element(val korean: String, val color: String) {
        WOOD("목", "#00FF00"),
        FIRE("화", "#FF0000"),
        EARTH("토", "#A52A2A"),
        METAL("금", "#FFFFFF"),
        WATER("수", "#000000")
    }

    enum class HeavenlyStalk(val element: Element) {
        GAP(Element.WOOD), EUL(Element.WOOD),
        BYEONG(Element.FIRE), JEONG(Element.FIRE),
        MU(Element.EARTH), GI(Element.EARTH),
        GYEONG(Element.METAL), SIN(Element.METAL),
        IM(Element.WATER), GYE(Element.WATER)
    }

    enum class EarthlyBranch(val element: Element) {
        JA(Element.WATER), CHUK(Element.EARTH),
        IN(Element.WOOD), MYO(Element.WOOD),
        JIN(Element.EARTH), SA(Element.FIRE),
        OH(Element.FIRE), MI(Element.EARTH),
        SIN(Element.METAL), YU(Element.METAL),
        SUL(Element.EARTH), HAE(Element.WATER)
    }
}