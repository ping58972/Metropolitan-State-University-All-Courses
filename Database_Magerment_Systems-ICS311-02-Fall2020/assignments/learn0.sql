-- use university;

-- select * from student S join takes T on (S.ID = T.ID) order by name;

use nalongsonedanddank;

-- select * from customer C natural join invoice I where (C.cus_code = I.cus_code) order by cus_lname;

-- select * from customer C  join invoice I on (C.cus_code = I.cus_code) order by cus_lname;

-- select * from customer C right outer join invoice I on (C.cus_code = I.cus_code) order by cus_lname;

-- delimiter//
-- create trigger test_trig
-- after insert on employee
-- for each row
-- begin
-- 	declare @current_date int = ;
-- 	declare @DOB2 int;
-- //delimiter

-- create trigger before_insert
-- before insert on employee
-- for each row
-- insert into 

-- (select * from customer C left outer join invoice I on C.cus_code = I.cus_code
-- union all
-- (select * from customer C right outer join invoice I on C.cus_code = I.cus_code))  ;

-- grant select on invoice to ping_1@localhost;
revoke select on nalongsonedanddank.invoice from ping_1@localhost;
-- create user 'ping_1'@'localhost' identified by '58972';

