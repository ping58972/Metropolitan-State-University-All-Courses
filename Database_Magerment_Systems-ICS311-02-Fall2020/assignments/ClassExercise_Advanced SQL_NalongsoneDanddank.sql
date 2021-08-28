-- -- Class Exercise – Advanced SQL

-- -- Exercise 1:
use nalongsonedanddank;

drop trigger if exists insert_line_invoice ;
delimiter $$
create trigger insert_line_invoice
after insert on line
for each row
begin
declare invNum int;
declare cusCode int;
declare InvDate date;
set invNum = new.inv_number + 1 ;
insert into invoice(inv_number, cus_code, inv_date) values(invNum, 10010, curdate());
end $$
delimiter ;

insert into line(inv_number, prod_code, line_units) value(1006, 12322, 10);

-- -- Exercise 2:
use nalongsonedanddank;

drop table if exists Employee;
create table Employee(Emp_ID int primary key, DOB date);
drop table if exists Employee_info;
 create table Employee_Info(Emp_ID int primary key, Fname char(20), Lname char(20), Age int);

drop trigger if exists emp_age;
delimiter $$
create trigger emp_age
after insert on Employee
for each row
begin
declare count_age integer;
set count_age = timestampdiff(year, new.DOB, curdate());
update employee_info E set Age = count_age where E.Emp_ID = new.Emp_ID;
end $$
delimiter ;

insert into employee_info(Emp_ID, Fname, Lname, Age) value(1, 'Ping', 'Wang', 0); 
insert into employee(Emp_ID, DOB) value(1, '1986-12-14');
select * from employee_info;

-- -- Exercise 3:
use university;

drop procedure if exists department_proc;
delimiter $$
create procedure department_proc(in dname varchar(20), in _building varchar(15), in _budget float, in percentage float)
begin
declare inc_budget float;
set inc_budget = _budget + _budget*(percentage/100);
insert into department(dept_name, building, budget) value(dname, _building, inc_budget);
end $$
delimiter ;

call department_proc('department name', 'CS', 10000, 10);

-- -- Exercise 4:
use university;

drop procedure if exists dep_inst_proc;
delimiter $$
create procedure dep_inst_proc(in in_id int, in in_name varchar(20), in in_dept_name varchar(20), in in_salary numeric(8,2))
begin
declare dname varchar(20);
declare new_salary numeric(8,2);
select dept_name into dname from department where dept_name = 'Physics';
if dname = in_dept_name then
set new_salary = in_salary + in_salary*0.2;
else
set new_salary = in_salary;
end if;
insert into instructor(id,name,dept_name,salary) value(in_id, in_name, in_dept_name, new_salary);
end $$
delimiter ;

call dep_inst_proc(33333, 'Ping Wang','Physics', 90000);
call dep_inst_proc(33332, 'Pink Wang','History', 90000);

