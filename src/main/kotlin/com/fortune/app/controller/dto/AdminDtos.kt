package com.fortune.app.controller.dto

data class EventCreateRequest(
    val title: String,
    val description: String,
    val thumbnailImageUrl: String,
    val questions: List<QuestionCreateRequest>
)

data class QuestionCreateRequest(
    val content: String,
    val orderNo: Int,
    val options: List<OptionCreateRequest>
)

data class OptionCreateRequest(
    val text: String,
    val score: Int,
)