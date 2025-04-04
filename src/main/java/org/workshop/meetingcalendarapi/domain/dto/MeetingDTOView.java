package org.workshop.meetingcalendarapi.domain.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record MeetingDTOView(int id, String title, LocalDate date, LocalTime time, String participatns, String description) {
}
