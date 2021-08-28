-- drop database if exists practice_exam_mid;
-- create database practice_exam_mid;

use practice_exam_mid;

-- drop table if exists Customer;
-- create table Customer(
-- cus_code int not null unique, cus_lname varchar(20) not null, cus_fname varchar(20) not null,
-- cus_initial char(1), cus_areacode int, cus_phone int,
-- primary key(cus_code)
-- );

-- create table Invoice(inv_number int not null unique,
-- cus_code int, inv_date date, 
-- primary key(inv_number),
-- foreign key(cus_code) references Customer(cus_code) on delete set null
-- );

-- alter table Customer add column email varchar(20) after cus_phone;
-- insert into Customer value(124,'ksdj', 'kdjf', 'N', 234, 2342343, 'psior@ksj');
-- insert into Customer value(125,'ksdj', 'kdjf', 'N', 234, 2342343, 'psior@ksj');
-- update customer set email = 'skdfjfjdfkd@iii' where cus_code = 124;
-- delete from customer where cus_code = 124;
 -- delete from Customer where cus_code = 125;
-- delete from customer where cus_code > 0;
-- alter table customer modify column email varchar(50);
alter table customer drop column cus_initial;
select * from customer;
-- truncate customer;
