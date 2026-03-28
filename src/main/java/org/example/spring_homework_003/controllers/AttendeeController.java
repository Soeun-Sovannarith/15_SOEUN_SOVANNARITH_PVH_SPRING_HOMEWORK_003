package org.example.spring_homework_003.controllers;

import org.example.spring_homework_003.models.entity.Attendee;
import org.example.spring_homework_003.models.reponses.Response;
import org.example.spring_homework_003.models.request.AttendeeRequest;
import org.example.spring_homework_003.services.AttendessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendee")
public class AttendeeController {
    private final AttendessService attendessService;


    public AttendeeController(AttendessService attendessService) {
        this.attendessService = attendessService;
    }


    @GetMapping()
    public ResponseEntity<?> getAllAttendess(@RequestParam Integer page, @RequestParam Integer size){
        List<Attendee> payload =attendessService.getAllAttendess(page,size);
        return ResponseEntity.accepted().body(Response.ResponseSuccess("Retrieved attendees successfully", "OK",payload));
    }

    @GetMapping("{attendeeId}")
    public ResponseEntity<Response<Attendee>> getAttendeeById(@PathVariable Integer attendeeId){
        Attendee payload = attendessService.getAttendeeById(attendeeId);
        return ResponseEntity.accepted().body(Response.ResponseSuccess("Retrieved attendee with id 1 successfully", "OK", payload));

    }

    @PostMapping()
    public ResponseEntity<?> createAttendee(@RequestBody AttendeeRequest newAttendee){
        Attendee payload = attendessService.createAttendee(newAttendee);
        return ResponseEntity.accepted().body(Response.ResponseSuccess("Created attendee successfully", "OK", payload));
    }

    @PutMapping("{attendeeId}")
    public ResponseEntity<Response<Attendee>> updateAttendee(@PathVariable Integer attendeeId, @RequestBody AttendeeRequest attendee){
        Attendee payload = attendessService.updateAttendee(attendeeId, attendee);
        return ResponseEntity.accepted().body(Response.ResponseSuccess("Updated attendee successfully", "OK", payload));
    }

    @DeleteMapping("{attendeeId}")
    public ResponseEntity<Response<Attendee>> deleteAttendee(@PathVariable Integer attendeeId){
        Attendee payload = attendessService.deleteAttendee(attendeeId);
        return ResponseEntity.accepted().body(Response.ResponseSuccess("Deleted attendee successfully", "OK", payload));
    }
}
