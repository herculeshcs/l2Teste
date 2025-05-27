-- Insert sample data into TITLE table
INSERT INTO TITLE (name) VALUES 
('Professor'),
('Associate Professor'),
('Assistant Professor'),
('Lecturer'),
('Adjunct Professor');

-- Insert sample data into DEPARTMENT table
INSERT INTO DEPARTMENT (name) VALUES 
('Computer Science'),
('Mathematics'),
('Physics'),
('Engineering'),
('Biology');

-- Insert sample data into PROFESSOR table
INSERT INTO PROFESSOR (department_id, title_id, name) VALUES 
(1, 1, 'Dr. Smith'),
(1, 2, 'Dr. Johnson'),
(2, 1, 'Dr. Williams'),
(3, 3, 'Dr. Brown'),
(4, 2, 'Dr. Jones'),
(5, 1, 'Dr. Garcia');

-- Insert sample data into BUILDING table
INSERT INTO BUILDING (name) VALUES 
('Science Building'),
('Engineering Complex'),
('Mathematics Hall'),
('Main Campus Building'),
('Biology Research Center');

-- Insert sample data into SUBJECT table
INSERT INTO SUBJECT (code, name, department_id) VALUES 
('CS101', 'Introduction to Programming', 1),
('CS201', 'Data Structures', 1),
('CS301', 'Algorithms', 1),
('MATH101', 'Calculus I', 2),
('MATH201', 'Linear Algebra', 2),
('PHYS101', 'General Physics', 3),
('ENG101', 'Introduction to Engineering', 4),
('BIO101', 'General Biology', 5);

-- Insert sample data into SUBJECT_PREREQUISITE table
INSERT INTO SUBJECT_PREREQUISITE (subject_id, prerequisite_id) VALUES 
(2, 1), -- CS201 requires CS101
(3, 2), -- CS301 requires CS201
(5, 4); -- MATH201 requires MATH101

-- Insert sample data into ROOM table
INSERT INTO ROOM (building_id, room_number, capacity) VALUES 
(1, '101', 30),
(1, '201', 25),
(2, '105', 40),
(3, '301', 35),
(4, '102', 50),
(5, '205', 20);

-- Insert sample data into CLASS table
INSERT INTO CLASS (subject_id, year, semester, code, professor_id) VALUES 
(1, 2023, 'Fall', 'CS101-01', 1),
(1, 2023, 'Fall', 'CS101-02', 2),
(2, 2023, 'Fall', 'CS201-01', 1),
(4, 2023, 'Fall', 'MATH101-01', 3),
(6, 2023, 'Fall', 'PHYS101-01', 4),
(8, 2023, 'Fall', 'BIO101-01', 6);

-- Insert sample data into CLASS_SCHEDULE table
INSERT INTO CLASS_SCHEDULE (class_id, room_id, day_of_week, start_time, end_time) VALUES 
(1, 1, 'Monday', '09:00:00', '10:30:00'),
(1, 1, 'Wednesday', '09:00:00', '10:30:00'),
(2, 2, 'Tuesday', '13:00:00', '14:30:00'),
(2, 2, 'Thursday', '13:00:00', '14:30:00'),
(3, 3, 'Monday', '11:00:00', '12:30:00'),
(3, 3, 'Wednesday', '11:00:00', '12:30:00'),
(4, 4, 'Tuesday', '10:00:00', '11:30:00'),
(4, 4, 'Thursday', '10:00:00', '11:30:00'),
(5, 5, 'Friday', '14:00:00', '16:00:00'),
(6, 6, 'Monday', '13:00:00', '15:00:00');
