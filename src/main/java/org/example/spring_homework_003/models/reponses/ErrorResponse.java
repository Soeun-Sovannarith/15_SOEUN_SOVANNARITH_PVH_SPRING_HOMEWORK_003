package org.example.spring_homework_003.models.reponses;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String detail;
    private String instance;
    private int status;
    private String title;
    private String type;
    private Instant timestamp;
    private Map<String, String> errors;

}