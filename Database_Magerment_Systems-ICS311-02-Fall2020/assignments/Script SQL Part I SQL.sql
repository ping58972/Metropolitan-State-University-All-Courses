-- CREATE DATABASE --

create database university;
show databases;
drop database university;

create database university;
use university;

-- CREATE TABLE DEPARTMENT--
create table department
	(dept_name varchar(20), 
	 building varchar(15), 
	 budget numeric(12,2),
	 primary key (dept_name)
	);

describe department;

-- CREATE TABLE INSTRUCTOR --

create table instructor (id varchar(5), 
	 name varchar(20), 
	 dept_name varchar(20), 
	 salary numeric(8,2),
	 primary key (ID),
	 foreign key (dept_name) references department (dept_name)
	);
    
desc instructor;

-- CONSTRAINTS : DEFAULT --
create table classroom
	(building varchar(15),
	 room_number varchar(7) DEFAULT '100',
	 capacity numeric(4,0),
	 primary key (building, room_number)
	);

desc classroom;
    
select building, room_number, capacity from classroom;    
insert into classroom(building, capacity) values ('Painter', 1500);
insert into classroom(building, room_number, capacity) values ('Painter', NULL, 1500);    -- This statement will fail. Why?
select building, room_number, capacity from classroom;

-- CONSTRAINTS : CHECK --
drop table instructor; -- Since it is related to department table
drop table department; -- Since it already exists
 
create table department
	(dept_name varchar(20), 
	 building varchar(15), 
	 budget numeric(12,2) check (budget > 1000),          
	 primary key (dept_name)
	);

desc department;   
select dept_name, building, budget from department;
 
insert into department(dept_name, building, budget) values ('Physics', 'Watson', 2500);    -- This will work 
insert into department(dept_name, building, budget) values ('History', 'Taylor', 500);     -- This will not work

-- CONSTRAINTS : NOT NULL --
create table instructor (id varchar(5), 
	 name varchar(20) not null, 
	 dept_name varchar(20), 
	 salary numeric(8,2),
	 primary key (ID),
	 foreign key (dept_name) references department (dept_name)
	);
 
 select id, dept_name, salary from instructor;
 
 insert into instructor(id, dept_name, salary) values ('33456', 'Physics', '87000');   -- This will not work
 insert into instructor(id, name, dept_name, salary) values ('12121', NULL, 'Physics', '90000');    -- This will not work
 insert into instructor(id, name, dept_name, salary) values ('12121', 'Gold', 'Physics', '90000');    -- This will work!
 select * from instructor;
 
 
 -- Class Exercise
 create table student 
        (id varchar(5), 
	 name varchar(20) not null, 
	 dept_name varchar(20), 
	 tot_cred numeric(3,0) check (tot_cred >= 0),
	 primary key (ID),
	 foreign key (dept_name) references department (dept_name) on delete set null
	);
 
 create table course
	(course_id varchar(8), 
	 title varchar(50), 
	 dept_name varchar(20),
	 credits numeric(2,0) check (credits > 0),
	 primary key (course_id),
	 foreign key (dept_name) references department (dept_name) on delete set null
	);

create table section
	(course_id varchar(8), 
         sec_id varchar(8),
	 semester varchar(6) check (semester in ('Fall', 'Winter', 'Spring', 'Summer')), 
	 year numeric(4,0) check (year > 1701 and year < 2100), 
	 building varchar(15),
	 room_number varchar(7),
	 time_slot_id varchar(4),
	 primary key (course_id, sec_id, semester, year),
	 foreign key (course_id) references course (course_id) on delete cascade,
	 foreign key (building, room_number) references classroom (building, room_number) on delete set null
	);
	
create table teaches 
         (id			 varchar(5), 
	 course_id		 varchar(8),
	 sec_id			 varchar(8), 
	 semester		 varchar(6),
	 year			 numeric(4,0),
	 primary key (ID, course_id, sec_id, semester, year),
	 foreign key (course_id,sec_id, semester, year) references section (course_id,sec_id, semester, year) on delete cascade,
	 foreign key (ID) references instructor (ID) on delete cascade
	);	
 
  -- ALTER TABLE --
 
desc department;
alter table department modify budget integer;

drop table student;
create table student 
	(id varchar(5), 
	 name varchar(20) not null, 
	 dept_name varchar(20), 
	 tot_cred numeric(3,0),
	 primary key (ID),
	 foreign key (dept_name) references department (dept_name)
	);
    
desc student;
alter table student add middleInitial varchar(1);
alter table student  drop middleInitial;

-- DML : INSERT -- 
delete from instructor;
delete from department;
select * from department;

insert into department values ('Biology', 'Watson', '90000');
insert into department values ('Comp. Sci.', 'Taylor', '100000');
insert into department values ('Elec. Eng.', 'Taylor', '85000');
insert into department(dept_name, building, budget) values ('Finance', 'Painter', '120000');
insert into department(dept_name, building, budget) values ('History', 'Painter', '50000');
insert into department(dept_name, building, budget) values ('Music', 'Packard', '80000');
insert into department(dept_name, budget) values ('Physics', '70000'); 
 
-- DML : UPDATE -- 
insert into instructor values ('10101', 'Srinivasan', 'Comp. Sci.', '65000');
insert into instructor values ('12121', 'Wu', 'Finance', '90000');
insert into instructor values ('15151', 'Mozart', 'Music', null);
insert into instructor values ('22222', 'Einstein', 'Physics', '95000');
insert into instructor values ('32343', 'El Said', 'History', '60000');
insert into instructor values ('33456', 'Gold', 'Physics', '87000');
insert into instructor values ('45565', 'Katz', 'Comp. Sci.', '75000');
insert into instructor values ('58583', 'Califieri', 'History', '62000');
insert into instructor values ('76543', 'Singh', 'Finance', '80000');
insert into instructor values ('76766', 'Crick', 'Biology', '72000');
insert into instructor values ('83821', 'Brandt', 'Comp. Sci.', null);
insert into instructor values ('98345', 'Kim', 'Elec. Eng.', '80000');

select * from instructor;
select * from instructor where dept_name = 'History';
 
update instructor
set    salary = salary * 1.1
where  dept_name = 'History';

update instructor
set    salary = salary * 1.1;

-- DML : DELETE -- 
 
delete from instructor where name = 'Wu';
delete from instructor where salary < 80000;
 
-- NATURAL JOIN --
-- Drop and re-create University database, Run Univ_Insert First --
 select * from instructor natural join teaches;
 
 select * from instructor, teaches
 where instructor.id = teaches.id;

-- *** --

select name, course_id 
from instructor natural join teaches;

select name, course_id 
from instructor, teaches 
where instructor.ID = teaches.ID;

-- *** --

-- Incorrect version (makes course.dept_name = instructor.dept_name)
select * -- name, title 
from instructor natural join course natural join teaches;

-- Correct version
select * -- name, title 
from instructor natural join teaches, course 
where teaches.course_id = course.course_id;



--  Using Subquery
-- Finad Title and credits for courses taught by instructors from computer science department
select id from instructor where dept_name = 'Comp. Sci.';
select * from teaches where id in (select id from instructor where dept_name = 'Comp. Sci.');
select * from course where course_id in (select course_id from teaches where id in (select id from instructor where dept_name = 'Comp. Sci.'));


 
 -- Class Exercise 1 --
select title 
from  course 
where dept_name = 'Comp. Sci.'
and   credits = 3;

 -- Class Exercise 2 --
select distinct course.title
from  instructor natural join teaches, course 
where teaches.course_id = course.course_id
and   instructor.name = 'Brandt';

select distinct course.title
from  instructor, teaches, course 
where instructor.ID = teaches.ID 
and   teaches.course_id = course.course_id
and   instructor.name = 'Brandt';

 -- Class Exercise 3 --
select  student.name , instructor.name 
from   student, advisor, instructor
where  student.ID = advisor.s_id 
and    advisor.i_id = instructor.ID order by 2,1;


