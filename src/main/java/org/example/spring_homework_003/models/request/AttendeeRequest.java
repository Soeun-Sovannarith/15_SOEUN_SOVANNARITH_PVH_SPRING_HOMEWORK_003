package org.example.spring_homework_003.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {

    @NotBlank(message = "Attendee name must contain only letters and spaces")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Attendee name must contain only letters and spaces")
    private String attendeeName;

    @NotBlank(message = "Email must be a valid format (e.g., abc@gmail.com)")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email must be a valid format (e.g., abc@gmail.com)")
    private String email;
}
