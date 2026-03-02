package org.example.com.fortune.app.repository

import org.example.com.fortune.app.domain.result.TestResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestResultRepository : JpaRepository<TestResult, Long>{
    fun findByResultCode(resultCode: String): TestResult?
}