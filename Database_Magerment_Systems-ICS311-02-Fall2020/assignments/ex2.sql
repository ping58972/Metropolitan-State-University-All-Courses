-- select S.sid, S.sname from Seilors S, Reserves R, Boats B where S.sid = R.sid and B.bid = R.bid and B.color = 'red' 
-- not in (select S.sid from Sailors S, Boats B, Reserves R where S.sid = R.sid and R.bid = B.bid and B.color = B.color= 'green');

-- select avg(salary) from instructor where dept_name='Comp. Sci.';

-- -- select count(distinct *) from instructor, course as C as I where I.id = C.id in (select * from course where course.

-- select count(distinct ID) from teaches where semester='Spring' and year=2010;

-- select count(*) from course where course_id like 'CS%';

use university;

-- select course_id from section where semester = 'Fall' and year = 2009
-- union
--  (select course_id from section where semester='Spring' or year= 2010);
 
 select count(*) from course where course_id like 'CS%';