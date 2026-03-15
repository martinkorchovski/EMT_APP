insert into countries (id, name)
values (1, 'Macedonia'),
       (2, 'Germany'),
       (3, 'France'),
       (4, 'Italy'),
       (5, 'Spain'),
       (6, 'United Kingdom');

insert into states (id, name)
values (1, 'GOOD'),
       (2, 'BAD');

insert into categories (id, name)
values (1, 'HOTEL'),
       (2, 'APARTMENT'),
       (3, 'HOUSE'),
       (4, 'VILLA'),
       (5, 'HOSTEL');

insert into hosts (id, name, surname, country_id)
values (1, 'Stefan', 'Petrov', 1),
       (2, 'Hans', 'Muller', 2),
       (3, 'Pierre', 'Dubois', 3),
       (4, 'Marco', 'Rossi', 4),
       (5, 'Carlos', 'Garcia', 5),
       (6, 'John', 'Smith', 6);

insert into accommodations (id, name, num_rooms, category_id, state_id)
values (1, 'Hotel Skopje', 30, 1, 1),
       (2, 'City Apartment', 5, 2, 1),
       (3, 'Mountain House', 8, 3, 2),
       (4, 'Luxury Villa', 15, 4, 1),
       (5, 'Youth Hostel', 20, 5, 2),
       (6, 'Lake Apartment', 4, 2, 1),
       (7, 'Central Hotel', 40, 1, 1);

insert into accommodations_hosts (accommodation_id, host_id)
values (1, 1),
       (2, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 2),
       (7, 3);