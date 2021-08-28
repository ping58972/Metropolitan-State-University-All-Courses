-- create database exam_mid;

-- use exam_mid;

-- create table Branch(branchName varchar(15), street varchar(30), city varchar(20),
-- primary key(branchName));

-- create table Account(accountNum int, branchName varchar(15), balance int,
-- primary key(accountNum));

-- create table Customer(customerSSN int, street varchar(30), city varchar(20),
-- primary key(customerSSN));

-- create table Deposit(customerSSN int, accountNum int, amount int,
-- primary key(customerSSN, accountNum),
-- foreign key(customerSSN) references Customer(customerSSN) on delete cascade,
-- foreign key(accountNum) references Account(accountNum) on delete cascade);

-- use exam_mid;

-- insert into Branch values("First Bank", "first Street", "Crystal"),
-- ("Second Bank", "Second Street", "New Hope"),
-- ("Third Bank", "Third Street", "Golden Valley");

-- insert into Account values(1, "First Bank", 100), 
-- (2, 'Second Bank', 200), (3,"Third Bank", 300);

-- insert into Customer values (123, "Fourth Street", "Edina"),
-- (456, "Fifth Street", "Plymouth" ), (789, "Sixth Street", "Wayzata" );

-- insert into Deposit values(123, 1, 100), (456, 2, 200), (789, 3,300);

-- select CBP.cname from 
-- (select * from Customer C ,Buys B , Product P where B.pid = P.pid  and C.cid = B.cid) CBP
-- Where (CBP.manufactrued = ‘Target’) and (CBP.price between 25 and 50);

-- select CBP.type and CBP.price from
-- (select * from Customer C ,Buys B , Product P where B.pid = P.pid  and C.cid = B.cid) CBP
-- where (CBP.age > 32) and (CBP.quantity > 100);

-- update Product set price = price - price*0.03;

-- select cname from Customer where age is null;

use nalongsonedanddank;

-- select * from
-- ((select C.cus_lname, C.cus_fname, C.cus_areacode, I.inv_number 
-- from Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 713) 
-- union
-- (select C.cus_lname, C.cus_fname, C.cus_areacode, I.inv_number 
-- from  Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 615)) PD;

-- select * from
-- in SQL server
-- (select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 713) 
-- except
-- (select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from  Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 615);

-- -- in Oracle
-- (select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 713) 
-- MINUS
-- (select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from  Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 615);

-- in MySQL
-- select cus_code, cus_lname, cus_fname, cus_areacode, cus_phone, inv_date  from
-- (select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 713) C
-- left join
-- (select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from  Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 615) D
-- on C.cus_code = D.cus_code ;

-- select *  from
-- select C.cus_code, C.cus_lname, C.cus_fname, C.cus_areacode, C.cus_phone, I.inv_date 
-- from Customer C, Invoice I where C.cus_code = I.cus_code and C.cus_areacode = 713 
-- and (C.cus_areacode not in (select cus_areacode from Customer where cus_areacode = 615));

-- select max(visitCost) from Recoeds;

-- select DPR.doctorId, DPR.doctorName, sum(DPR.numberOfVisits) from
-- (select * from Doctors D, Patients P, Records R where D.doctorId = R.doctorId and P.patientId = R.patientId) DPR
-- group by DPR.doctorId;

-- create view DoctorSmithsPatients as
-- select doctorId, patientName, visitCost from
-- (select * from Doctors D, Patients P, Records R where D.doctorId = R.doctorId and P.patientId = R.patientId) DPR
-- where DPR.doctorName = 'Smith';

-- select * from DoctorSmithsPatients;

select * from customer C, invoice I, line L where C.cus_code = I.cus_code and I.inv_number = L.inv_number;