package org.workshop.meetingcalendarapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.workshop.meetingcalendarapi.domain.model.UserRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class CalendarUser {
    @NonNull
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @NonNull
    @Column(updatable = false, unique = true)
    private String email;
    @NonNull
    @Column(nullable = false)
    private String password;
    @NonNull
    @Column(nullable = false)
    private UserRole role;
}
