package org.example.com.fortune.app.controller

import com.fortune.app.controller.dto.EventCreateRequest
import org.example.com.fortune.app.service.EventService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController (
    private val eventService: EventService
) {
    @PostMapping("/events")
    fun createNewEvent(@RequestBody request: EventCreateRequest): ResponseEntity<Long> {
        val eventId = eventService.createEvent(request)
        return ResponseEntity.ok(eventId)
    }
}