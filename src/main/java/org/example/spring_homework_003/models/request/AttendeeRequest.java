package org.example.spring_homework_003.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {

    @NotBlank(message = "Attendee name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Attendee name must contain only letters and spaces")
    @Schema(type = "string", example = "Rith", pattern = "")
    private String attendeeName;

    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email must be a valid format (e.g., abc@gmail.com)")
    @Schema(type = "string", example = "rith@gmail.com", pattern = "")
    private String email;
}
