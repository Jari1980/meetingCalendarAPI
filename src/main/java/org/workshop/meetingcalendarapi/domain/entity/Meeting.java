package org.workshop.meetingcalendarapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.workshop.meetingcalendarapi.domain.model.MeetingLevel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Meeting {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private LocalDate date;
    @NonNull
    private LocalTime time;
    @NonNull
    private MeetingLevel level;
    private String participants; //This could be changed to list of users later on, if/when I add users
    private String description;
}
