package org.example.spring_homework_003.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer eventId;
    private String eventName;
    private Instant eventDate;
    private List venueList;
}
