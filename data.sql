
INSERT INTO venues (venue_name, location) VALUES
                                              ('Grand Hall', 'Phnom Penh'),
                                              ('Conference Center', 'Siem Reap'),
                                              ('Tech Hub', 'Battambang');


INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('AI Conference', '2026-04-10', 1),
                                                          ('Startup Pitch', '2026-05-15', 2),
                                                          ('Coding Bootcamp', '2026-06-01', 3);


INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Rith Sok', 'rith@gmail.com'),
                                                 ('Dareach Chan', 'dareach@gmail.com'),
                                                 ('Sophea Lim', 'sophea@gmail.com'),
                                                 ('Vanna Kim', 'vanna@gmail.com');


INSERT INTO event_attendee (attendee_id, event_id) VALUES
                                                       (1, 1),
                                                       (2, 1),
                                                       (3, 2),
                                                       (4, 3),
                                                       (1, 3),
                                                       (2, 2);