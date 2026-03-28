package org.example.spring_homework_003.services.Impl;

import org.example.spring_homework_003.exception.DuplicationException;
import org.example.spring_homework_003.exception.ResourceNotFoundException;
import org.example.spring_homework_003.models.entity.Attendee;
import org.example.spring_homework_003.models.request.AttendeeRequest;
import org.example.spring_homework_003.repositories.AttendessReposiory;
import org.example.spring_homework_003.services.AttendessService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;



@Service
public class AttendessServiceImpl implements AttendessService {

    private final AttendessReposiory attendessReposiory;

    public AttendessServiceImpl(AttendessReposiory attendessReposiory) {
        this.attendessReposiory = attendessReposiory;
    }


    @Override
    public List<Attendee> getAllAttendess(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return attendessReposiory.getAllAttendess(offset,size);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee attendee = attendessReposiory.getAttendeeById(attendeeId);
        if(attendee != null){
            return attendee;
        } else {
            throw new ResourceNotFoundException("Attendee with "+attendee.getAttendeeId()+" was not found");
        }
    }

    @Override
    public Attendee createAttendee(AttendeeRequest newAttendee) {

        Attendee nameConflict = attendessReposiory.getAttendeeByName(newAttendee.getAttendeeName());
        if (nameConflict != null) {
            throw new DuplicationException("Attendee name already exists");
        }

        Attendee emailConflict = attendessReposiory.getAttendeeByEmail(newAttendee.getEmail());
        if (emailConflict != null) {
            throw new DuplicationException("Attendee email already exists");
        }


        return attendessReposiory.createAttendee(newAttendee);
    }



    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        if (attendeeRequest == null) {
            throw new IllegalArgumentException("Attendee name and email are required");
        }
        Attendee nameConflict = attendessReposiory.getAttendeeByName(attendeeRequest.getAttendeeName());
        if (nameConflict != null && !nameConflict.getAttendeeId().equals(attendeeId)) {
            throw new DuplicationException("Attendee name already exists");
        }

        Attendee emailConflict = attendessReposiory.getAttendeeByEmail(attendeeRequest.getEmail());
        if (emailConflict != null && !emailConflict.getAttendeeId().equals(attendeeId)) {
            throw new DuplicationException("Email is already exists");
        }

        return attendessReposiory.updateAttendee(attendeeId, attendeeRequest);
    }

    @Override
    public Attendee deleteAttendee(Integer attendeeId) {
        Attendee existing = getAttendeeById(attendeeId);
        if(existing == null){
            throw new ResourceNotFoundException("Attendee with id "+attendeeId+" was not found");
        }
        return attendessReposiory.deleteAttendee(attendeeId);
    }


}
