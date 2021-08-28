
-- create database university;
-- use university; -- Use your own database name e.g. ics311fa180301

-- drop table if exists prereq;
-- drop table if exists time_slot;
-- drop table if exists advisor;
-- drop table if exists takes;
-- drop table if exists student;
-- drop table if exists teaches;
-- drop table if exists section;
-- drop table if exists instructor;
-- drop table if exists course;
-- drop table if exists classroom; 
-- drop table if exists department;

-- Exercise 1:

-- create table classroom
-- 	(building varchar(15),
-- 	 room_number varchar(7),
-- 	 capacity numeric(4,0),
-- 	 primary key (building, room_number)
-- 	);
--     
-- create table department
-- 	(dept_name varchar(20), 
-- 	 building varchar(15), 
-- 	 budget numeric(12,2) check (budget > 0),
-- 	 primary key (dept_name)
-- 	);
--     
--     create table student 
--         (id varchar(5), 
-- 	 name varchar(20) not null, 
-- 	 dept_name varchar(20), 
-- 	 tot_cred numeric(3,0) check (tot_cred >= 0),
-- 	 primary key (ID),
-- 	 foreign key (dept_name) references department (dept_name) on delete set null
-- 	);
--         create table course
-- 	(course_id varchar(8), 
-- 	 title varchar(50), 
-- 	 dept_name varchar(20),
-- 	 credits numeric(2,0) check (credits > 0),
-- 	 primary key (course_id),
-- 	 foreign key (dept_name) references department (dept_name) on delete set null
-- 	);
--     
--     create table section
-- 	(course_id varchar(8), 
--          sec_id varchar(8),
-- 	 semester varchar(6) check (semester in ('Fall', 'Winter', 'Spring', 'Summer')), 
-- 	 year numeric(4,0) check (year > 1701 and year < 2100), 
-- 	 building varchar(15),
-- 	 room_number varchar(7),
-- 	 time_slot_id varchar(4),
-- 	 primary key (course_id, sec_id, semester, year),
-- 	 foreign key (course_id) references course (course_id) on delete cascade,
-- 	 foreign key (building, room_number) references classroom (building, room_number) on delete set null
-- 	);
--     
--     create table takes
-- 	(id varchar(5),
--          course_id varchar(8),
-- 	 sec_id varchar(8), 
-- 	 semester varchar(6),
-- 	 year numeric(4,0),
-- 	 grade varchar(2),
-- 	 primary key (id, course_id, sec_id, semester, year),
-- 	 foreign key (course_id,sec_id, semester, year) references section (course_id,sec_id, semester, year) on delete cascade,
-- 	 foreign key (id) references student (id) on delete cascade
-- 	);
    
--     create table instructor (id varchar(5), 
-- 	 name varchar(20) not null, 
-- 	 dept_name varchar(20), 
-- 	 salary numeric(8,2) check (salary > 29000),
-- 	 primary key (ID),
-- 	 foreign key (dept_name) references department (dept_name) on delete set null
-- 	);
    
-- Exercise 2:
-- 	insert into department values('Chemistry', 'Hofferman', 200000),('Radiology', 'Roosevelt', 270000),('Pathology', 'Spalding', 320000);  
 -- 	insert into instructor values(1001, 'Johnson', 'Radiology', 100000),(1002, 'Smith', 'Chemistry', null);
--  insert into instructor values(1003, 'Johnson', 'Pathology', 125000);

-- Exercise 3:
select * from course where dept_name = 'Comp. Sci.' and credits = 3 ;
-- select * from 

select student.name, instructor.name from student