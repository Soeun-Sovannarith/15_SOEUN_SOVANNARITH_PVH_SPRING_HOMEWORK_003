
DROP TABLE IF EXISTS event_attendee;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS attendees;
DROP TABLE IF EXISTS venues;


CREATE TABLE venues (
                        venue_id SERIAL PRIMARY KEY,
                        venue_name VARCHAR(255) NOT NULL,
                        location VARCHAR(255) NOT NULL
);


CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(255) NOT NULL,
                        event_date DATE NOT NULL,
                        venue_id INT,
                        CONSTRAINT fk_venue
                            FOREIGN KEY (venue_id)
                                REFERENCES venues(venue_id)
                                ON DELETE SET NULL
                                ON UPDATE CASCADE
);


CREATE TABLE attendees (
                           attendee_id SERIAL PRIMARY KEY,
                           attendee_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE event_attendee (
                                attendee_id INT,
                                event_id INT,
                                PRIMARY KEY (attendee_id, event_id),
                                CONSTRAINT fk_attendee
                                    FOREIGN KEY (attendee_id)
                                        REFERENCES attendees(attendee_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE,
                                CONSTRAINT fk_event
                                    FOREIGN KEY (event_id)
                                        REFERENCES events(event_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE
);