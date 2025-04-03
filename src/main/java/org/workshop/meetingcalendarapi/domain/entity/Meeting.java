package org.workshop.meetingcalendarapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime time;
    private String participants; //This could be changed to list of users later on, if/when I add users
    private String description;
}
