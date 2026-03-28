package org.example.spring_homework_003.services;

import org.example.spring_homework_003.models.entity.Attendee;
import org.example.spring_homework_003.models.request.AttendeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendessService {
    List<Attendee> getAllAttendess(Integer page, Integer size);

    Attendee getAttendeeById(Integer attendeesId);

    Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendee);

    Attendee deleteAttendee(Integer attendeeId);

    Attendee createAttendee(AttendeeRequest newAttendee);
}
