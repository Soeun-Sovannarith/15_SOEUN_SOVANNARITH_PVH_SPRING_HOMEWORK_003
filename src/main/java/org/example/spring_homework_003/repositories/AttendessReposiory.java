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

    @Options(useGeneratedKeys = true, keyProperty = "attendeeId", keyColumn = "attendee_id")
    @Insert("Insert into attendees (attendee_name, email) values (#{attendeeName}, #{email});")
    int createAttendee(Attendee attendee);

    @Update("Update attendees set attendee_name = #{attendeeName}, email = #{email} where attendee_id = #{attendeeId};")
    int updateAttendee(Attendee attendee);

    @Delete("Delete from attendees where attendee_id = #{attendeeId};")
    int deleteAttendee(Integer attendeeId);



}
