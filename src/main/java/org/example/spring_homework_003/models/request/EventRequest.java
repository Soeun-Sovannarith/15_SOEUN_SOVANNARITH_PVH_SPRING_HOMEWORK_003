package org.example.spring_homework_003.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event name cannot be empty")
    private String eventName;

    @NotNull(message = "Event date cannot be empty")
    private Instant eventDate;

    @NotNull(message = "Venue ID cannot be empty")
    private Integer venueId;

    private List<Integer> attendeeIds;
}
