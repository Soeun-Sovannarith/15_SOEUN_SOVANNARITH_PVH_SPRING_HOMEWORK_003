package org.example.spring_homework_003.repositories;

import org.apache.ibatis.annotations.*;
import org.example.spring_homework_003.models.entity.Event;
import org.example.spring_homework_003.models.entity.Attendee;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("SELECT a.* FROM attendees a INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id WHERE ea.event_id = #{eventId}")
    @Results(id = "attendeesForEventMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    List<Attendee> getAttendeesByEventId(Integer eventId);

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.example.spring_homework_003.repositories.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "getAttendeesByEventId"))
    })
    @Select("SELECT * FROM events OFFSET #{offset} LIMIT #{size};")
    List<Event> getAllEvents(@Param("offset") Integer offset, @Param("size") Integer size);

    @ResultMap("eventMapper")
    @Select("SELECT * FROM events WHERE event_id = #{eventId};")
    Event getEventById(Integer eventId);

    @ResultMap("eventMapper")
    @Select("SELECT * FROM events WHERE event_name = #{eventName} LIMIT 1;")
    Event getEventByName(String eventName);

    @ResultMap("eventMapper")
    @Select("INSERT INTO events (event_name, event_date, venue_id) VALUES (#{req.eventName}, #{req.eventDate}, #{req.venueId}) RETURNING *;")
    Event createEvent(@Param("req") org.example.spring_homework_003.models.request.EventRequest event);

    @ResultMap("eventMapper")
    @Select("UPDATE events SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId} WHERE event_id = #{eventId} RETURNING *;")
    Event updateEvent(Integer eventId, @Param("req") org.example.spring_homework_003.models.request.EventRequest event);

    @ResultMap("eventMapper")
    @Select("DELETE FROM events WHERE event_id = #{eventId} RETURNING *;")
    Event deleteEvent(Integer eventId);

    @Insert("<script>" +
            "INSERT INTO event_attendee (event_id, attendee_id) VALUES " +
            "<foreach collection='attendeeIds' item='attendeeId' separator=','>" +
            "(#{eventId}, #{attendeeId})" +
            "</foreach>" +
            "</script>")
    void insertEventAttendees(@Param("eventId") Integer eventId, @Param("attendeeIds") List<Integer> attendeeIds);

    @Delete("DELETE FROM event_attendee WHERE event_id = #{eventId}")
    void deleteEventAttendees(Integer eventId);
}
