package org.example.com.fortune.app.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 * MappedSuperclass를 통해 공통 필드를 상속
 * @EntityListeners(AuditingEntityListener::class)를 사용하여 JPA가 시간을 자동 주입합니다.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    var createdAt : LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAtL : LocalDateTime = LocalDateTime.now()
        protected set
}