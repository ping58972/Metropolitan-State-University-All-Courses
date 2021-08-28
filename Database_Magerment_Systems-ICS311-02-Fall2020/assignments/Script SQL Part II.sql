use university;

-- SET Operations --
-- UNION -- 
select course_id  from section where semester = 'Fall' and year = 2009
union
select course_id from section where semester = 'Spring' and year = 2010
order by course_id;

--  EXCEPT
(select course_id from section where semester = 'Fall' and year = 2009)
except
(select course_id from section where semester = 'Spring' and year = 2010);

-- MySQL does not support EXCEPT. Instead, use nested query
select course_id  from section where semester = 'Fall' and year = 2009
and course_id not in 
               (select course_id from section where semester = 'Spring' and year = 2010)
order by course_id;


-- INTERSECT --
select course_id from section where semester = 'Fall' and year = 2009
intersect
select course_id from section where semester = 'Spring' and year = 2010;

--  MySQL does not support INTERSECT. Instead, use nested query
select course_id  from section where semester = 'Fall' and year = 2009
and course_id in 
               (select course_id from section where semester = 'Spring' and year = 2010)
order by course_id;





-- rename or Alias
Select ID, name, salary/12 AS monthly_salary
from instructor;

Select distinct T.name, D.building
from instructor as T, department as D
where T.dept_name = D.dept_name
and  D.dept_name = 'Comp. Sci.';

Select ID, name, salary/12 monthly_salary
from instructor;               
 
Select ID, name, Round(salary/12,2) "Monthly Salary"
from instructor; 





               
-- Nested Subqueries --
-- USING IN and NOT IN --
select course_id from section where semester = 'Fall' and year= 2009 
and course_id in (select course_id from section where semester = 'Spring' and year= 2010);

select course_id from section where semester = 'Fall' and year= 2009 
and course_id not in (select course_id from section where semester = 'Spring' and year= 2010);





use university;
-- NULL --
select name,salary from instructor where salary = null;    -- Incorrect! Produces no result.
select name,salary from instructor where salary is null;
select name, salary from instructor where salary is not null;

select * from department where budget is null;
select * from department where budget > 90000  and budget is not null;



-- DISTINCT --
select distinct dept_name from instructor;




-- String Operations --
select name from instructor where name like 'C%';
select name from instructor where name like '%ing%';
select name from instructor where name like 'K_tz';

select concat(name, ',', dept_name) AS INST_DETAILS from instructor;
select dept_name, lower(dept_name) from instructor;
select name, upper(name) from instructor;



-- ORDER BY --
select * from instructor order by name asc;
select * from instructor order by name;
select * from instructor order by name desc;
select * from instructor order by 2;
select * from instructor order by 3,2;
select * from instructor order by 3 desc,2;





select name, salary from instructor where salary between 70000 and 90000;





-- AGGREGATE FUNCTIONS --
select avg (salary) from instructor where dept_name= 'Comp. Sci.';
select count(distinct ID) from teaches where semester = 'Spring' and year = 2010;
select count(*) from course;

select * from instructor order by 3;
select dept_name, avg(salary) from instructor group by dept_name;
select dept_name, ID, avg (salary) from instructor group by dept_name;   /* Incorrect Results */
select dept_name, avg (salary) from instructor group by dept_name having avg (salary) > 75000;

select sum(salary) from instructor;

select * from instructor order by dept_name;
select distinct name from instructor where name in ('Mozart', 'Einstein');
select distinct name from instructor where name not in ('Mozart', 'Einstein');

select distinct T.name from instructor T, instructor S 
where T.salary > S.salary and S.dept_name = 'History';






-- GROUP BY --
select dept_name, avg (salary) from instructor group by dept_name;

/* erroneous query */
select dept_name, ID, avg (salary) from instructor group by dept_name;

/* Correct query */
select dept_name, ID, avg (salary) from instructor group by dept_name, ID;

select dept_name, avg (salary)
from instructor
group by dept_name
having avg (salary) > 42000;






-- Using SOME, ANY, ALL --
select * from instructor;

select distinct T.name                 -- SELF JOIN --
from instructor T, instructor S
where T.salary > S.salary and S.dept_name = 'History';

select name,salary, dept_name from instructor 
where salary > some (select salary from instructor where dept_name = 'History');

select name,salary, dept_name from instructor 
where salary > any (select salary from instructor where dept_name = 'History');

select name,salary, dept_name from instructor 
where salary > all (select salary from instructor where dept_name = 'History');






-- Subqueries in FROM clause (ALIAS TABLES) --
select S.dept_name, S.avg_salary
from (select dept_name, avg(salary) as avg_salary
            from instructor
            group by dept_name) S
where S.avg_salary > 42000;





-- DELETE --

delete from instructor where dept_name= 'Finance';
delete from instructor where dept_name in (select dept_name from department where building = 'Watson');
delete from instructor where salary < (select avg (salary) from instructor);
delete from instructor;





-- INSERT --
insert into student select ID, name, dept_name, 0 from instructor;
select * from student;

insert into student select ID, name, dept_name from instructor;





-- UPDATE --
update instructor set salary = salary * 1.03 where salary > 90000;          
update instructor set salary = salary * 1.05 where salary <= 60000;

update instructor set salary = 
	case
	when salary <= 80000 then salary * 1.05 
	else salary * 1.03
end;

