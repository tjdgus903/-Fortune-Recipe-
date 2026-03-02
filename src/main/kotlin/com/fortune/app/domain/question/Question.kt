package com.fortune.app.domain.question

import jakarta.persistence.*

/**
 * 질문 및 선택지 (Question & Option)
 * 질문 하나에 여러 선택지가 붙는 1:N 구조
 */
@Entity
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val content: String, // 질문 내용
    val orderNo: Int,    // 노출 순서

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    val options: MutableList<QuestionOption> = mutableListOf()
)

@Entity
class QuestionOption(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val optionText: String, // 선택지 텍스트
    val score: Int,         // 배점(결과 계산용)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    val question: Question
)