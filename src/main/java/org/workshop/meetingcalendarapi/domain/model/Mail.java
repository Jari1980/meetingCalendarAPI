package org.workshop.meetingcalendarapi.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Mail {
    private String name;
    private String message;
    private String email;
    private boolean broccoli;
}
