--- Segue o SQL que cria o banco que é mostrado na questão 2

create table building
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

create table department
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

create table room
(
    id          int auto_increment
        primary key,
    building_id int         not null,
    room_number varchar(20) not null,
    capacity    int         null,
    constraint room_ibfk_1
        foreign key (building_id) references building (id)
);

create index building_id
    on room (building_id);

create table subject
(
    id            int auto_increment
        primary key,
    code          varchar(20)  not null,
    name          varchar(100) not null,
    department_id int          not null,
    constraint subject_ibfk_1
        foreign key (department_id) references department (id)
);

create index department_id
    on subject (department_id);

create table subject_prerequisite
(
    id              int auto_increment
        primary key,
    subject_id      int not null,
    prerequisite_id int not null,
    constraint subject_prerequisite_ibfk_1
        foreign key (subject_id) references subject (id),
    constraint subject_prerequisite_ibfk_2
        foreign key (prerequisite_id) references subject (id)
);

create index prerequisite_id
    on subject_prerequisite (prerequisite_id);

create index subject_id
    on subject_prerequisite (subject_id);

create table title
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

create table professor
(
    id            int auto_increment
        primary key,
    department_id int          not null,
    title_id      int          not null,
    name          varchar(100) not null,
    constraint professor_ibfk_1
        foreign key (department_id) references department (id),
    constraint professor_ibfk_2
        foreign key (title_id) references title (id)
);

create table class
(
    id           int auto_increment
        primary key,
    subject_id   int         not null,
    year         int         not null,
    semester     varchar(20) not null,
    code         varchar(20) not null,
    professor_id int         not null,
    constraint class_ibfk_1
        foreign key (subject_id) references subject (id),
    constraint class_ibfk_2
        foreign key (professor_id) references professor (id)
);

create index professor_id
    on class (professor_id);

create index subject_id
    on class (subject_id);

create table class_schedule
(
    id          int auto_increment
        primary key,
    class_id    int         not null,
    room_id     int         not null,
    day_of_week varchar(10) not null,
    start_time  time        not null,
    end_time    time        not null,
    constraint class_schedule_ibfk_1
        foreign key (class_id) references class (id),
    constraint class_schedule_ibfk_2
        foreign key (room_id) references room (id)
);

create index class_id
    on class_schedule (class_id);

create index room_id
    on class_schedule (room_id);

create index department_id
    on professor (department_id);

create index title_id
    on professor (title_id);

