-- -------Part I: ------------------
create database NalongsoneDanddank;

use NalongsoneDanddank;

drop table if exists Customer;
drop table if exists Invoice;
drop table if exists Vendor;
drop table if exists Product;
drop table if exists Line;

create table Customer(
cus_code integer,
cus_lname varchar(20) not null,
cus_fname varchar(20) not null,
cus_initial char(1),
cus_areacode integer,
cus_phone integer,
primary key(cus_code)
);

create table Invoice(
inv_number integer,
cus_code integer,
inv_date date,
primary key(inv_number),
foreign key (cus_code) references Customer(cus_code) on delete set null
);

create table Vendor(
vend_code integer,
vend_name varchar(30) not null,
vend_contact varchar(30) not null,
vend_areacode integer,
vend_phone integer,
primary key(vend_code)
);

create table Product(
prod_code integer,
prod_desc varchar(50),
prod_price integer not null,
prod_quant integer,
vend_code integer,
primary key(prod_code),
foreign key(vend_code) references Vendor(vend_code) on delete set null
);

create table Line(
inv_number integer,
prod_code integer,
line_units integer,
primary key(inv_number, prod_code),
foreign key(inv_number) references Invoice(inv_number),
foreign key(prod_code) references Product(prod_code)
);

-- ----------Part II ---------

-- 1. Customer:
insert into Customer(cus_code, cus_lname, cus_fname, cus_initial, cus_areacode, cus_phone) 
values(10010, 'Johnson', 'Alfred', 'A', 615, 8442573), (10011, 'Dunne', 'Leona', 'K', 713, 8941238),
(10012, 'Smith', 'Walter', 'W', 615, 8942285),(10013, 'Roberts', 'Paul', 'F', 615, 2221672),
(10014, 'Orlando', 'Myla', NULL, 615, 2971228);

-- 2. Invoice:
insert into Invoice(inv_number, cus_code, inv_date) values(1001, 10011, '2008-08-03'),
(1002, 10014, '2008-08-04'),(1003, 10012, '2008-03-20'),(1004, 10014, '2008-09-23');

-- 3. Vendor:
insert into Vendor(vend_code, vend_name, vend_contact, vend_areacode, vend_phone)
 values(232, 'Bryson', 'Smith', 615, 2233234),(235, 'Walls', 'Anderson', 615, 2158995),(236, 'Jason', 'Schmidt', 651, 2468850);

-- 4. Product:
insert into Product(prod_code, prod_desc, prod_price, prod_quant, vend_code) 
values(12321, 'hammer', 189 ,20, 232),(65781, 'chain', 12, 45, 235),
(34256, 'tape', 35, 60, 236),(12333, 'hanger', 200 ,10, 232);

-- 5. Line:
insert into Line(inv_number, prod_code, line_units) values(1001,  12321, 1),
(1001,  65781, 3),(1002,  34256, 6),(1003,  12321, 5),(1002,  12321, 6);


-- --------Part III------

-- insert into Customer(cus_code, cus_lname, cus_fname, cus_initial, cus_areacode, cus_phone) value(10012, 'Juan', 'Rodriguez', 'J', 612, 7788776);
-- -- the SQL statement action output Error, message: "Duplicate entry '10012' for key 'PRIMARY' "
-- --  that means cannot insert the row because there has already primary key, 10012, on the table. primary key cannot duplicate.

-- insert into Invoice(inv_number, cus_code, inv_date) values(1005, 10017, '2008-11-30');
-- -- the SQL statement action output Error, message: "Cannot add or update a child row: a foreign key Customer(cus_code) does not exist"
-- -- that means the foreign key cus_code that want to insert, 10017, does not exist.

-- insert into Product(prod_code, prod_desc, prod_price, prod_quant, vend_code) value(12322, 'hammer', 189, 20, 231);
-- -- the SQL statement action output Error, message: "Cannot add or update a child row: a foreign key Vender(vend_code) does not exist"
-- -- that means the foreign key cus_code that want to insert, 231, does not exist.

insert into Vendor(vend_code, vend_name, vend_contact, vend_areacode, vend_phone) values(231,'Adam','Eric', 615, 2158995);
-- -- the SQL statement action output succesful. However, if running this statement before the last one, 
-- -- it would be succesful because it missing "231",vend_code, foreign key(vend_code) references to the primary key of Vendor table.

insert into Product(prod_code, prod_desc, prod_price, prod_quant, vend_code) value(12322, 'coil', 189, 20, 231);
-- -- the SQL statement action output succesful. However, if running this statement before the last one, 
-- -- it would be unsuccess because it missing "231",vend_code, foreign key(vend_code) references to the primary key of Vendor table.


-- --------Part IV------

use NalongsoneDanddank;

select cus_code as 'Customer Code', cus_lname as 'Last naem', cus_fname as 'First name' from Customer;

select inv_number as 'Invoice number', inv_date as 'Invoice date' from Invoice where cus_code = 10014;

select prod_code as 'Product code', prod_quant as 'Product quantity' from Product natural join Line where inv_number = 1001;
select p.prod_code as 'Product code', p.prod_quant as 'Product quantity' from Product p, Line l where p.prod_code = l.prod_code and l.inv_number = 1001;

select prod_desc as 'Product description', prod_price as 'Product price' from Product natural join Vendor where vend_contact = 'Nobody';

select prod_desc as  'Product description', vend_name as 'Vendor name', vend_phone as 'Vendor phone' from Product natural join Vendor where prod_quant <= 60;

select prod_desc as 'Product description',  cus_fname as 'Customer firstname', cus_lname as 'Customer lastname' from 
 line  natural join invoice natural join customer natural join  product;

select prod_desc as 'Product description', group_concat(cus_fname, ' ', cus_lname separator '; ') as 'Customer fullname' 
from line  natural join invoice natural join customer natural join  product group by prod_desc;

