package org.example.spring_homework_003.models.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue name cannot be empty")
    private String venueName;

    @NotBlank(message = "Location cannot be empty")
    private String location;
}
