package org.example.com.fortune.app.repository

import com.fortune.app.domain.question.QuestionOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionOptionRepository : JpaRepository<QuestionOption, Long>