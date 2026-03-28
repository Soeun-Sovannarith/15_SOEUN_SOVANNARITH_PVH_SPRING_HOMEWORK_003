package org.example.spring_homework_003.services.Impl;

import org.example.spring_homework_003.exception.ResourceNotFoundException;
import org.example.spring_homework_003.exception.DuplicationException;
import org.example.spring_homework_003.models.entity.Venue;
import org.example.spring_homework_003.models.request.VenueRequest;
import org.example.spring_homework_003.repositories.VenueRepository;
import org.example.spring_homework_003.services.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new ResourceNotFoundException("Venue with id " + venueId + " was not found");
        }
        return venue;
    }

    @Override
    public Venue createVenue(VenueRequest newVenue) {
        Venue nameConflict = venueRepository.getVenueByName(newVenue.getVenueName());
        if (nameConflict != null) {
            throw new DuplicationException("Venue name already exists");
        }
        return venueRepository.createVenue(newVenue);
    }

    @Override
    public Venue updateVenue(Integer venueId, VenueRequest venueRequest) {
        if (venueRequest == null) {
            throw new IllegalArgumentException("Venue name and location are required");
        }
        Venue nameConflict = venueRepository.getVenueByName(venueRequest.getVenueName());
        if (nameConflict != null && !nameConflict.getVenueId().equals(venueId)) {
            throw new DuplicationException("Venue name already exists");
        }
        Venue updated = venueRepository.updateVenue(venueId, venueRequest);
        if (updated == null) {
            throw new ResourceNotFoundException("Venue with id " + venueId + " was not found");
        }
        return updated;
    }

    @Override
    public Venue deleteVenue(Integer venueId) {
        Venue existing = getVenueById(venueId);
        if (existing == null) {
            throw new ResourceNotFoundException("Venue with id " + venueId + " was not found");
        }
        return venueRepository.deleteVenue(venueId);
    }
}
