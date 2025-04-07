package org.workshop.meetingcalendarapi.domain.dto;

import jakarta.validation.constraints.NotBlank;
import org.workshop.meetingcalendarapi.domain.model.MeetingLevel;

import java.time.LocalDate;
import java.time.LocalTime;

public record ContactDTOForm(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Message is required")
        String message,
        @NotBlank(message = "Broccoli is required")
        boolean broccoli) {
}
