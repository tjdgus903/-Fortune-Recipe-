package org.example.com.fortune.app.service

import com.fortune.app.domain.event.Event
import com.fortune.app.domain.question.Question
import com.fortune.app.domain.question.QuestionOption
import com.fortune.app.controller.dto.EventCreateRequest
import com.fortune.app.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(
    private val eventRepository: EventRepository,
) {
    @Transactional
    fun createEvent(request: EventCreateRequest): Long {
        // 1. 이벤트 마스터 생성
        val event = Event(
            title = request.title,
            description = request.description,
            thumbnailImageUrl = request.thumbnailImageUrl
        )

        // 2. 질문 및 선택지 매핑 (루프 처리)
        request.questions.forEach { qReq ->
            val question = Question(
                content = qReq.content,
                orderNo = qReq.orderNo,
                event = event
            )

            qReq.options.forEach { oReq ->
                val option = QuestionOption(
                    optionText = oReq.text,
                    score = oReq.score,
                    question = question
                )
                question.options.add(option)
            }
            event.questions.add(question)
        }

        // 3. 최종 저장 (CascadeType.ALL에 의해 하위 질문/옵션도 자동 저장됨)
        return eventRepository.save(event).id!!
    }
}