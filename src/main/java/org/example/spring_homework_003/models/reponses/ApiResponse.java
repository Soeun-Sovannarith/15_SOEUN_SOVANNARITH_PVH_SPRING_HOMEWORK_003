package org.example.spring_homework_003.models.reponses;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse {

    private String detail;
    private String instance;
    private int status;
    private String title;
    private String type;
    private Instant timestamp;

}