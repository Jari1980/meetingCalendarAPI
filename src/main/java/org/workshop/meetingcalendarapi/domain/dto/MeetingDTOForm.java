package org.workshop.meetingcalendarapi.domain.dto;


import jakarta.validation.constraints.NotBlank;
import org.workshop.meetingcalendarapi.domain.model.MeetingLevel;

import java.time.LocalDate;
import java.time.LocalTime;

public record MeetingDTOForm(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Date is required")
        LocalDate date,
        @NotBlank(message = "Time is required")
        LocalTime time,
        @NotBlank(message = "Meeting level is required")
        MeetingLevel level,
        String participants,
        String description,
        int id) {}
