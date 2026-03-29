package org.example.spring_homework_003.models.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event name cannot be empty")
    @Schema(example = "Spring Boot Bootcamp")
    private String eventName;

    @NotNull(message = "Event date cannot be empty")
    @Future(message = "Event date must be in the future")
    @Schema(example = "2026-05-15")
    private LocalDate eventDate;

    @NotNull(message = "Venue ID cannot be empty")
    @Schema(example = "1")
    private Integer venueId;

    @Schema(example = "[1, 2, 3]")
    private List<Integer> attendeeIds;
}
