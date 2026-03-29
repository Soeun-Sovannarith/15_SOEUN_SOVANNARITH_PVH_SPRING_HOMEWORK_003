package org.example.spring_homework_003.controllers;


import org.example.spring_homework_003.exception.ResourceNotFoundException;
import org.example.spring_homework_003.models.entity.Venue;
import org.example.spring_homework_003.models.reponses.Response;
import org.example.spring_homework_003.models.request.VenueRequest;
import org.example.spring_homework_003.services.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@Validated
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }


    @GetMapping()
    public ResponseEntity<?> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive(message = "must be greater than 0") Integer page,
            @RequestParam(defaultValue="10") @Positive(message = "must be greater than 0") Integer size){
        List<Venue> payload = venueService.getAllVenues(page,size);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Retrieved venues successfully", "OK", payload));
    }


    @GetMapping("{venueId}")
    public ResponseEntity<?> getVenueById(@PathVariable @Positive(message = "must be greater than 0") Integer venueId){
        Venue payload = venueService.getVenueById(venueId);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Retrieved venue successfully", "OK", payload));
    }

    @PostMapping()
    public ResponseEntity<?> createVenue(@Valid @RequestBody VenueRequest newVenue){
        Venue payload = venueService.createVenue(newVenue);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Created venue successfully", "OK", payload));
    }

    @PutMapping("{venueId}")
    public ResponseEntity<?> updateVenue(@PathVariable @Positive(message = "must be greater than 0") Integer venueId, @Valid @RequestBody VenueRequest venueRequest){
        Venue payload = venueService.updateVenue(venueId, venueRequest);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Updated venue successfully", "OK", payload));
    }

    @DeleteMapping("{venueId}")
    public ResponseEntity<?> deleteVenue(@PathVariable @Positive(message = "must be greater than 0") Integer venueId){
        venueService.deleteVenue(venueId);
        return ResponseEntity.ok().body(Response.ResponseSuccess("Deleted venue successfully", "OK", null));
    }
}
