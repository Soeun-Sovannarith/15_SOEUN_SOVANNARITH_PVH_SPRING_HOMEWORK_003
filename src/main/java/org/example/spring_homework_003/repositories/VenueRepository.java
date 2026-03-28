package org.example.spring_homework_003.repositories;

import org.apache.ibatis.annotations.*;
import org.example.spring_homework_003.models.entity.Venue;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venuesMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location"),
    })
    @Select("SELECT * FROM venues OFFSET #{offset} LIMIT #{size};")
    List<Venue> getAllVenues(Integer offset, Integer size);

    @ResultMap("venuesMapper")
    @Select("SELECT * FROM venues WHERE venue_id = #{venueId};")
    Venue getVenueById(Integer venueId);

    @ResultMap("venuesMapper")
    @Select("SELECT * FROM venues WHERE venue_name = #{venueName} LIMIT 1;")
    Venue getVenueByName(String venueName);

    @ResultMap("venuesMapper")
    @Select("INSERT INTO venues (venue_name, location) VALUES (#{req.venueName}, #{req.location}) RETURNING *;")
    Venue createVenue(@Param("req") org.example.spring_homework_003.models.request.VenueRequest venue);

    @ResultMap("venuesMapper")
    @Select("UPDATE venues SET venue_name = #{req.venueName}, location = #{req.location} WHERE venue_id = #{venueId} RETURNING *;")
    Venue updateVenue(Integer venueId, @Param("req") org.example.spring_homework_003.models.request.VenueRequest venue);

    @ResultMap("venuesMapper")
    @Select("DELETE FROM venues WHERE venue_id = #{venueId} RETURNING *;")
    Venue deleteVenue(Integer venueId);
}
