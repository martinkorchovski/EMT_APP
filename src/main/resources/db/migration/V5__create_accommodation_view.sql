CREATE OR REPLACE VIEW accommodation_view AS
SELECT
    a.id AS id,
    a.name AS name,
    c.name AS category,
    a.num_rooms AS numRooms,
    CONCAT(h.name, ' ', h.surname) AS hostFullName,
    co.name AS country
FROM accommodations a
         JOIN categories c ON a.category_id = c.id
         JOIN hosts h ON a.host_id = h.id
         JOIN countries co ON h.country_id = co.id;