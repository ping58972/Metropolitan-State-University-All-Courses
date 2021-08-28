-- -- Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
-- -- Email: nalongsone.danddank@my.metrostate.edu\
-- -- Metropolitan State University
-- -- ICS 311 —Database Management Systems
-- -- Homework #6


use assignment6;

-- -- 1)(4 Points) Create a view named “ATL_CHARTERS_V” that includes the following 
-- -- “For each charter of aircraft to Atlanta (STL), print the charter date, charter hours flown,
-- --  and the corresponding customer last name, first name, area code, and phone number”.
-- --  Your answer should include both the SQL statement for view creating along with the contents 
-- -- of the view (you get the contents of the view by select * from ATL-CHARTERS_V).
drop view if exists ATL_CHARTERS_V;
create view ATL_CHARTERS_V as 
select CHAR_DATE, CHAR_HOURS_FLOWN, CUS_LNAME, CUS_FNAME, CUS_AREACODE, CUS_PHONE 
from (select * from charter ch natural join ac_customer ac 
where (ch.CUS_CODE = ac.CUS_CODE) and (CHAR_DESTINATION = 'ATL')) T;

select * from ATL_CHARTERS_V;

-- -- 2)(4 Points) Modify the MODEL table to add the following attribute:
-- --  (Note: use ALTER TABLE and UPDATE commands for this question.)
alter table model
add column MOD_LIFT_WETGHT numeric(10,2) 
after MOD_CHG_MILE;

-- -- Once the attribute has been added, update the values of all rows as per following values:
update model set MOD_LIFT_WETGHT = 10000 where MOD_CODE = 'DC-90A';
update model set MOD_LIFT_WETGHT = 5000 where MOD_CODE = 'MA23-250';
update model set MOD_LIFT_WETGHT = 20000 where MOD_CODE = 'PA31-950';
select * from model;

-- -- 3)(4 Points) Create a trigger named trg_charter_hours that 
-- -- will automatically update the AIRCRAFT table after a new CHARTER row is added. 
-- -- Use the CHARTER table’s CHAR_HOURS_FLOWN to update the 
-- -- AIRCRAFT table’s AC_TTAF, AC_TTEL, and AC_TTER values. 

drop trigger if exists trg_charter_hours;
delimiter $$
create trigger trg_charter_hours
after insert on charter
for each row
begin
update aircraft set AC_TTAF = (AC_TTAF + new.CHAR_HOURS_FLOWN), 
AC_TTEL = (AC_TTEL + new.CHAR_HOURS_FLOWN), AC_TTER = (AC_TTER + new.CHAR_HOURS_FLOWN)
where AC_NUMBER = new.AC_NUMBER;
end $$
delimiter ;

insert into charter(CHAR_TRIP, CHAR_DATE, AC_NUMBER, CHAR_DESTINATION, 
CHAR_DISTANCE, CHAR_HOURS_FLOWN, CHAR_HOURS_WAIT, CHAR_TOT_CHG, CHAR_OIL_QTS, CUS_CODE)
VALUES(10019,'2008-02-05','2289L','ATL',936,10,2.2,354.1,1,10011);

-- -- 4)(4 Points) Create a trigger named trg_cust_balance that will automatically 
-- -- update the AC_CUSTOMER table’s CUS_BALANCE before a new CHARTER row is added. 
-- -- Use the CHARTER table’s CHAR_TOT_CHG as the update source (Assume that all charter 
-- -- charges are charged to the customer balance.)  In addition to the CHAR_TOT_CHG, 
-- -- add $25 for every quart of oil used on the charter. 

drop trigger if exists trg_cust_balance;
delimiter $$
create trigger trg_cust_balance
before insert on charter
for each row
begin
update ac_customer set CUS_BALANCE = (CUS_BALANCE + (new.CHAR_TOT_CHG + new.CHAR_OIL_QTS*25))
where CUS_CODE = new.CUS_CODE;
end $$
delimiter ;

insert into charter(CHAR_TRIP, CHAR_DATE, AC_NUMBER, CHAR_DESTINATION, 
CHAR_DISTANCE, CHAR_HOURS_FLOWN, CHAR_HOURS_WAIT, CHAR_TOT_CHG, CHAR_OIL_QTS, CUS_CODE)
VALUES(10022,'2008-02-05','2289L','ATL',936,10,2.2,100,2,10011);

-- -- 5)(4 Points) Create a stored procedure to update model charge per mile attribute.  
-- -- Procedure takes the model number as a parameter.  
-- -- The procedure increases the charge for this model by 25%.
drop procedure if exists prc_update_model_charge_mile;
delimiter $$
create procedure prc_update_model_charge_mile(in modelCode varchar(20))
begin
update model set MOD_CHG_MILE = (MOD_CHG_MILE + MOD_CHG_MILE*0.25) where MOD_CODE = modelCode;
end $$
delimiter ;

call prc_update_model_charge_mile('DC-90A');

-- -- 6)(4 Points)  Create a stored procedure that will take an Employee number and percentage, 
-- -- then update the corresponding employee’s hourly salary by the input percentage 
-- -- (increase the hourly salary, so you are giving the employee a raise).  
-- -- Hint: Alter Employee table to add the hourly_salary field, update it with a 
-- -- value of 30 for all rows in the table, before creating the procedure.

alter table employee
add column HOUR_SALARY double 
not null default 30
after EMP_HIRE_DATE;

drop procedure if exists prc_raise_emp_salary;
delimiter $$
create procedure prc_raise_emp_salary(in empNum int, in perc double)
begin
update employee set HOUR_SALARY = (HOUR_SALARY + HOUR_SALARY*perc) where EMP_NUM = empNum;
end $$
delimiter ;

call prc_raise_emp_salary(100, 0.2);










