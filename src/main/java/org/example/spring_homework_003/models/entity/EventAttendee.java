package org.example.spring_homework_003.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendee {
    private Integer attendeeId;
    private Integer eventId;

}
