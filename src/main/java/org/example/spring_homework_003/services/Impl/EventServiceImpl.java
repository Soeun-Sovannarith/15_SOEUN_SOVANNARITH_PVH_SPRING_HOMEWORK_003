package org.example.spring_homework_003.services.Impl;

import org.example.spring_homework_003.exception.ResourceNotFoundException;
import org.example.spring_homework_003.exception.DuplicationException;
import org.example.spring_homework_003.models.entity.Event;
import org.example.spring_homework_003.models.request.EventRequest;
import org.example.spring_homework_003.repositories.EventRepository;
import org.example.spring_homework_003.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new ResourceNotFoundException("Event with id " + eventId + " was not found");
        }
        return event;
    }

    @Override
    public Event createEvent(EventRequest newEvent) {
        Event nameConflict = eventRepository.getEventByName(newEvent.getEventName());
        if (nameConflict != null) {
            throw new DuplicationException("Event name already exists");
        }
        Event createdEvent = eventRepository.createEvent(newEvent);

        if (newEvent.getAttendeeIds() != null && !newEvent.getAttendeeIds().isEmpty()) {
            eventRepository.insertEventAttendees(createdEvent.getEventId(), newEvent.getAttendeeIds());
        }
        return eventRepository.getEventById(createdEvent.getEventId());
    }

    @Override
    public Event updateEvent(Integer eventId, EventRequest eventRequest) {
        if (eventRequest == null) {
            throw new IllegalArgumentException("Event request data is required");
        }
        Event nameConflict = eventRepository.getEventByName(eventRequest.getEventName());
        if (nameConflict != null && !nameConflict.getEventId().equals(eventId)) {
            throw new DuplicationException("Event name already exists");
        }
        Event updated = eventRepository.updateEvent(eventId, eventRequest);
        if (updated == null) {
            throw new ResourceNotFoundException("Event with id " + eventId + " was not found");
        }

        if (eventRequest.getAttendeeIds() != null) {
            eventRepository.deleteEventAttendees(eventId);
            if (!eventRequest.getAttendeeIds().isEmpty()) {
                eventRepository.insertEventAttendees(eventId, eventRequest.getAttendeeIds());
            }
        }

        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event deleteEvent(Integer eventId) {
        Event existing = getEventById(eventId);
        return eventRepository.deleteEvent(eventId);
    }
}
