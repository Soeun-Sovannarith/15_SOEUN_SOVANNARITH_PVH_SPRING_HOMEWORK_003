package org.example.spring_homework_003.services;


import org.example.spring_homework_003.models.entity.Venue;
import org.example.spring_homework_003.models.request.VenueRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenueService {
    List<Venue> getAllVenues(Integer page, Integer size);
    Venue getVenueById(Integer venueId);

    Venue createVenue(VenueRequest newVenue);
    Venue updateVenue(Integer venueId, VenueRequest venueRequest);
    Venue deleteVenue(Integer venueId);
}
