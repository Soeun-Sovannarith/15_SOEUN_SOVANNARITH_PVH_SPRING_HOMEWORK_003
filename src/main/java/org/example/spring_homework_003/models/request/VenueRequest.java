package org.example.spring_homework_003.models.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue name cannot be empty")
    @Schema(example = "Silicon Valley Tech Center")
    private String venueName;

    @NotBlank(message = "Location cannot be empty")
    @Schema(example = "California, USA")
    private String location;
}
