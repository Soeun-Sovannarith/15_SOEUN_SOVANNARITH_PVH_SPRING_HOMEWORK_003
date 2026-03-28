package org.example.spring_homework_003.controllers;

import org.example.spring_homework_003.models.entity.Event;
import org.example.spring_homework_003.models.reponses.Response;
import org.example.spring_homework_003.models.request.EventRequest;
import org.example.spring_homework_003.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllEvents(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue="10") Integer size){
        List<Event> payload = eventService.getAllEvents(page,size);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Retrieved events successfully", "OK", payload));
    }

    @GetMapping("{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable Integer eventId){
        Event payload = eventService.getEventById(eventId);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Retrieved event successfully", "OK", payload));
    }

    @PostMapping()
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventRequest newEvent){
        Event payload = eventService.createEvent(newEvent);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Created event successfully", "OK", payload));
    }

    @PutMapping("{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer eventId, @Valid @RequestBody EventRequest eventRequest){
        Event payload = eventService.updateEvent(eventId, eventRequest);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Updated event successfully", "OK", payload));
    }

    @DeleteMapping("{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer eventId){
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Deleted event successfully", "OK", null));
    }
}
