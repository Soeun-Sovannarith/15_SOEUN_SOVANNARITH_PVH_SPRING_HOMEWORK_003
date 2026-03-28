package org.example.spring_homework_003.models.reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response <T>{
    private Instant timestamp;
    private String message;
    private String Status;
    private T payload;

    public static <T> Response<T> ResponseSuccess(String msg, String status, T payload) {
        return new Response<>(Instant.now(), msg, status, payload);
    }


}
