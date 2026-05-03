CREATE MATERIALIZED VIEW accommodation_stats_view AS
SELECT
    c.name AS category,
    COUNT(a.id) AS totalAccommodations,
    SUM(a.num_rooms) AS totalRooms,
    AVG(a.num_rooms) AS avgRooms
FROM accommodations a
         JOIN categories c ON a.category_id = c.id
GROUP BY c.name;