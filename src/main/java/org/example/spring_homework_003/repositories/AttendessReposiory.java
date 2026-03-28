package org.example.spring_homework_003.repositories;

import org.apache.ibatis.annotations.*;
import org.example.spring_homework_003.models.entity.Attendee;
import org.example.spring_homework_003.models.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendessReposiory {

    @Results(id = "attendeesMapper", value = {
            @Result(property = "attendeeId", column = "attendee_Id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email"),

    })
    @Select("Select * from attendees OFFSET #{offset} LIMIT #{size};")
    List<Attendee> getAllAttendess(@Param("offset")Integer offset, @Param("size")Integer size);

    @ResultMap("attendeesMapper")
    @Select("Select * from attendees where attendee_id = #{attendeeId};")
    Attendee getAttendeeById(Integer attendeeId);

    @ResultMap("attendeesMapper")
    @Select("Select * from attendees where attendee_name = #{attendeeName} LIMIT 1;")
    Attendee getAttendeeByName(String attendeeName);

    @ResultMap("attendeesMapper")
    @Select("Insert into attendees (attendee_name, email) values (#{req.attendeeName}, #{req.email}) RETURNING *;")
    Attendee createAttendee(@Param("req")AttendeeRequest attendee);

    @ResultMap("attendeesMapper")
    @Select("Update attendees set attendee_name = #{req.attendeeName}, email = #{req.email} where attendee_id = #{attendeeId} RETURNING *;")
    Attendee updateAttendee(Integer attendeeId ,@Param("req")AttendeeRequest attendee);

    @ResultMap("attendeesMapper")
    @Select("Delete from attendees where attendee_id = #{attendeeId} RETURNING *;")
    Attendee deleteAttendee(Integer attendeeId);

    @ResultMap("attendeesMapper")
    @Select("Select * from attendees where email = #{email} LIMIT 1;")
    Attendee getAttendeeByEmail(String email);
}
