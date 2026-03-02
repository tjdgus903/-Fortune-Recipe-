package com.fortune.app.domain.event

import jakarta.persistence.*
import com.fortune.app.domain.question.Question
import java.time.LocalDateTime

/**
 * [포트폴리오 포인트]
 * 설문조사 엔진의 핵심인 '이벤트(캠페인)' 관리 엔티티입니다.
 * 이 구조를 통해 소스 코드 수정 없이 DB 데이터만으로
 * '사주 운세', 'MBTI 테스트', '신년 이벤트' 등을 독립적으로 운영할 수 있습니다.
 */
@Entity
class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,                  // 이벤트 제목 (예 : 2026 신년 운세 레시피)

    @Column(columnDefinition = "TEXT")
    var description: String,            // 이벤트 상세 설명

    var thumbnailTimageURL: String,     // 랜딩 페이지에 노출될 메인 이미지

    var status: EventStatus = EventStatus.READY,    // READY, OPEN, CLOSED 상태 관리

    var startedAt: LocalDateTime,
    var endedAt: LocalDateTime,

    /**
     * OneToMany 는 이벤트 하나에 여러 질문을 가지기 위해서
     * CascadeType.ALL 이벤트 저장 시 질문들도 영속화 되게끔 구성
     */
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    var questions: MutableList<Question> = mutableListOf()
)

enum class EventStatus { READY, OPEN, CLOSED }