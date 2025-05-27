--- A quantidade de horas que cada professor tem comprometido em aulas - Então faça uma consulta SQL que traga essa informação.
select
    p.id AS professor_id,
    p.name AS professor_name,
    d.name AS department,
    t.name AS title,
    COUNT(DISTINCT cs.class_id) AS number_of_classes,
    sum(time_to_sec(timediff( cs.end_time,cs.start_time))) /3600 AS total_time_in_hours
from professor p
    join  department d on p.department_id=d.id
    join title t on p.title_id=t.id
    join class c on p.id = c.professor_id
    join class_schedule cs on c.id = cs.class_id
    group by p.id, p.name,d.name,t.name

