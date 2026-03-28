package org.example.spring_homework_003.services;

import org.example.spring_homework_003.models.entity.Event;
import org.example.spring_homework_003.models.request.EventRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);
    Event getEventById(Integer eventId);

    Event createEvent(EventRequest newEvent);
    Event updateEvent(Integer eventId, EventRequest eventRequest);
    Event deleteEvent(Integer eventId);
}
