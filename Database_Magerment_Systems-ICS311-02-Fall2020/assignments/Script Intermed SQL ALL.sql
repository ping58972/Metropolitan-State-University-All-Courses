-- Self Join --
use university;
select * from customer;


select distinct B.cus_lname, B.cus_fname, B.cus_areacode
from customer A, customer b
where a.cus_code != b.cus_code
and   a.cus_areacode = b.cus_areacode
order by 1;


use university;

/* Cartesian */
select * from student; 
select * from takes;
select * from student S, takes T where S.ID = T.ID order by name;

/* Left Outer Join */
select * from student S left outer join takes T on (S.ID = T.ID) order by name;

/* Right Outer Join */
select * from takes T right outer join  student  S on (S.ID = T.ID) order by name;

select * from course order by course_id;
select * from prereq order by prereq_id;

/* Cartesian */
select * from course C join prereq P on (C.course_id = P.course_id) order by 1;

/* Left Outer Join */
select * from course C left outer join prereq P on (C.course_id = P.course_id) order by 1;

/* Right Outer Join */
select * from prereq P right outer join course C on (C.course_id = P.course_id) order by 1;

/* Inner Join - Same as Join */
select * from course C inner join prereq P on (C.course_id = P.course_id) order by 1;
select * from course C natural join prereq P order by 1;

-- JOIN Using Customer database
use customer;

select * from customer;
select * from invoice;
select * from line;
select * from product;
select * from vendor;

select *
from customer, invoice;

select *
from customer natural join invoice;

select * from customer C inner join invoice I on C.cus_code = I.cus_code;

select *
from customer C left outer join invoice I on C.cus_code = I.cus_code;

select *
from customer C right outer join invoice I on C.cus_code = I.cus_code;

/* Views */
use university;
-- First drop the views created earlier for a fresh start --
drop view faculty;
drop view biologyFaculty;
drop view departments_total_salary;
drop view physics_fall_2009;
drop view physics_fall_2009_watson;
drop view instructor_info;
drop view instructor_depinfo;
drop view history_instructors;

select * from instructor;

create view faculty as 
select ID, name, dept_name from instructor;

select * from faculty;

create view biologyFaculty as 
select name, dept_name from instructor where dept_name = 'Biology';

select * from biologyfaculty;

create view departments_total_salary (dept_name, total_salary) as
select dept_name, sum(salary) from instructor
group by dept_name;

select * from departments_total_salary;

/* Create Views using Views */
select * from course;
select * from section;

create view cs_spring_2010 as
select course.course_id, sec_id, building, room_number
from course, section
where course.course_id = section.course_id
and course.dept_name = 'Comp. Sci.'
and section.semester = 'Spring'
and section.year = '2010';

select * from cs_spring_2010;

create view cs_spring_2010_watson as
select course_id, room_number
from cs_spring_2010
where building= 'Watson';

select * from  cs_spring_2010_watson;


/* View DML - Inserting into Views */
select * from faculty;
select * from instructor;
insert into faculty values ('30766', 'Green', 'Music');
select * from faculty;

select * from department;

create view instructor_info as
select ID, name, building
from instructor I, department D
where I.dept_name= D.dept_name;

select * from instructor_info;

insert into instructor_info(ID, name, building) values ('69987', 'White', 'Taylor');

/* View with dept_name */
create view instructor_depinfo as
select ID, name, D.dept_name, building
from instructor I, department D
where I.dept_name= D.dept_name;

select * from instructor_depinfo;

insert into instructor_depinfo(ID, name, dept_name, building) values ('69987', 'White', 'Comp. Sci.', 'Taylor');

/* Check View Condition */
create view history_instructors as
select * from instructor where dept_name= 'History';

select * from instructor;
select * from history_instructors;

insert into history_instructors values ('25566', 'Brown', 'Biology', 100000); 
select * from instructor;
select * from history_instructors;


/* For Cascade */

 /*1 No cascade */
select * from department1;
select * from course1;
select * from instructor1;

delete from department1 where dept_name = 'Biology';  -- This will fail, why? Because of referential integrity!

delete from course1 where dept_name = 'Biology';
delete from instructor1 where dept_name = 'Biology';
delete from department1 where dept_name = 'Biology';  -- Now, it will work!

select * from department1;
select * from course1;
select * from instructor1;

/*1 Set Null*/
select * from department2;
select * from course2;
select * from instructor2;

delete from department2 where dept_name = 'Biology';     -- Delete will set dept_name to NULL 

select * from department2;
select * from course2;
select * from instructor2;

/*1 Cascade*/
select * from department3;
select * from course3;
select * from instructor3;

delete from department3 where dept_name = 'Biology';     -- Cascade delete from all tables

select * from department3;
select * from course3;
select * from instructor3; 