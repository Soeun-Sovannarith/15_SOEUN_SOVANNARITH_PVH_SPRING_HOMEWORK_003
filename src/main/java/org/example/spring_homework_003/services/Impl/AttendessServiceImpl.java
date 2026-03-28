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
            throw new ResourceNotFoundException("Attendee with id 18 not found");
        }
    }

    @Override
    public Attendee createAttendee(AttendeeRequest newAttendee) {
        if(newAttendee == null || !StringUtils.hasText(newAttendee.getAttendeeName()) || !StringUtils.hasText(newAttendee.getEmail())){
            throw new IllegalArgumentException("Attendee name and email are required");
        }
        Attendee conflict = attendessReposiory.getAttendeeByName(newAttendee.getAttendeeName());
        if(conflict != null){
            throw new DuplicationException("Attendee name already exists");
        }
        Attendee attendee = new Attendee();
        attendee.setAttendeeName(newAttendee.getAttendeeName());
        attendee.setEmail(newAttendee.getEmail());
        int inserted = attendessReposiory.createAttendee(attendee);
        if(inserted != 1){
            throw new RuntimeException("Failed to create attendee");
        }
        return attendessReposiory.getAttendeeById(attendee.getAttendeeId());
    }



    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        if(attendeeRequest == null || !StringUtils.hasText(attendeeRequest.getAttendeeName()) || !StringUtils.hasText(attendeeRequest.getEmail())){
            throw new IllegalArgumentException("Attendee name and email are required");
        }
        Attendee conflict = attendessReposiory.getAttendeeByName(attendeeRequest.getAttendeeName());
        if(conflict != null && !conflict.getAttendeeId().equals(attendeeId)){
            throw new DuplicationException("Attendee name already exists");
        }
        Attendee attendee = getAttendeeById(attendeeId);
        attendee.setAttendeeName(attendeeRequest.getAttendeeName());
        attendee.setEmail(attendeeRequest.getEmail());
        int updated = attendessReposiory.updateAttendee(attendee);
        if(updated != 1){
            throw new RuntimeException("Failed to update attendee");
        }
        return attendessReposiory.getAttendeeById(attendeeId);
    }

    @Override
    public Attendee deleteAttendee(Integer attendeeId) {
        Attendee existing = getAttendeeById(attendeeId);
        int deleted = attendessReposiory.deleteAttendee(attendeeId);
        if(deleted != 1){
            throw new RuntimeException("Failed to delete attendee");
        }
        return existing;
    }


}
