---Lista de salas com horários livres e ocupados - Pode usar SQL e a linguagem de programação que achar melhor.

SELECT
    r.id AS room_id,
    b.name AS building_name,
    r.room_number,
    days.day_of_week,
    slots.time_slot,
    CASE
        WHEN cs.id IS NOT NULL THEN 'Ocupado'
        ELSE 'Livre'
        END AS status
FROM
    (SELECT 'Monday' AS day_of_week UNION ALL
     SELECT 'Tuesday' UNION ALL
     SELECT 'Wednesday' UNION ALL
     SELECT 'Thursday' UNION ALL
     SELECT 'Friday') days
        CROSS JOIN
    (SELECT '08:00-10:00' AS time_slot UNION ALL
     SELECT '10:00-12:00' UNION ALL
     SELECT '13:00-15:00' UNION ALL
     SELECT '15:00-17:00') slots
        CROSS JOIN
    ROOM r
        INNER JOIN
    BUILDING b ON r.building_id = b.id
        LEFT JOIN
    CLASS_SCHEDULE cs ON r.id = cs.room_id
        AND days.day_of_week = cs.day_of_week
        AND (
                             (slots.time_slot = '08:00-10:00' AND cs.start_time <= '08:00:00' AND cs.end_time >= '10:00:00') OR
                             (slots.time_slot = '10:00-12:00' AND cs.start_time <= '10:00:00' AND cs.end_time >= '12:00:00') OR
                             (slots.time_slot = '13:00-15:00' AND cs.start_time <= '13:00:00' AND cs.end_time >= '15:00:00') OR
                             (slots.time_slot = '15:00-17:00' AND cs.start_time <= '15:00:00' AND cs.end_time >= '17:00:00')
                             )
ORDER BY
    r.id,
    FIELD(days.day_of_week, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'),
    slots.time_slot;
