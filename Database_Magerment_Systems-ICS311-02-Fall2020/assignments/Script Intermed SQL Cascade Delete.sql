/* For Cascade */
use university;

drop table if exists  course1;
drop table if exists  instructor1;
drop table if exists  department1;
drop table if exists  course2;
drop table if exists  instructor2;
drop table if exists  department2;
drop table if exists  course3;
drop table if exists  instructor3;
drop table if exists  department3;


create table department1
	(dept_name varchar(20), 
	 building varchar(15), 
	 budget numeric(12,2) check (budget > 0),
	 primary key (dept_name)
	);
    
create table course1
	(course_id varchar(8), 
	 title varchar(50), 
	 dept_name varchar(20),
	 credits numeric(2,0) check (credits > 0),
	 primary key (course_id),
	 foreign key (dept_name) references department1 (dept_name) 
	);

create table instructor1 (id varchar(5), 
	 name varchar(20) not null, 
	 dept_name varchar(20), 
	 salary numeric(8,2) check (salary > 29000),
	 primary key (ID),
	 foreign key (dept_name) references department1 (dept_name) 
	);

insert into department1 values ('Biology', 'Watson', '90000');
insert into department1 values ('Comp. Sci.', 'Taylor', '100000');
insert into department1 values ('Elec. Eng.', 'Taylor', '85000');
insert into department1 values ('Finance', 'Painter', '120000');
insert into department1 values ('History', 'Painter', '50000');
insert into department1 values ('Music', 'Packard', '80000');
insert into department1 values ('Physics', 'Watson', '70000');
insert into course1 values ('BIO-101', 'Intro. to Biology', 'Biology', '4');
insert into course1 values ('BIO-301', 'Genetics', 'Biology', '4');
insert into course1 values ('BIO-399', 'Computational Biology', 'Biology', '3');
insert into course1 values ('CS-101', 'Intro. to Computer Science', 'Comp. Sci.', '4');
insert into course1 values ('CS-190', 'Game Design', 'Comp. Sci.', '4');
insert into course1 values ('CS-315', 'Robotics', 'Comp. Sci.', '3');
insert into course1 values ('CS-319', 'Image Processing', 'Comp. Sci.', '3');
insert into course1 values ('CS-347', 'Database System Concepts', 'Comp. Sci.', '3');
insert into course1 values ('EE-181', 'Intro. to Digital Systems', 'Elec. Eng.', '3');
insert into course1 values ('FIN-201', 'Investment Banking', 'Finance', '3');
insert into course1 values ('HIS-351', 'World History', 'History', '3');
insert into course1 values ('MU-199', 'Music Video Production', 'Music', '3');
insert into course1 values ('PHY-101', 'Physical Principles', 'Physics', '4');
insert into instructor1 values ('10101', 'Srinivasan', 'Comp. Sci.', '65000');
insert into instructor1 values ('12121', 'Wu', 'Finance', '90000');
insert into instructor1 values ('15151', 'Mozart', 'Music', '40000');
insert into instructor1 values ('22222', 'Einstein', 'Physics', '95000');
insert into instructor1 values ('32343', 'El Said', 'History', '60000');
insert into instructor1 values ('33456', 'Gold', 'Physics', '87000');
insert into instructor1 values ('45565', 'Katz', 'Comp. Sci.', '75000');
insert into instructor1 values ('58583', 'Califieri', 'History', '62000');
insert into instructor1 values ('76543', 'Singh', 'Finance', '80000');
insert into instructor1 values ('76766', 'Crick', 'Biology', '72000');
insert into instructor1 values ('83821', 'Brandt', 'Comp. Sci.', '92000');
insert into instructor1 values ('98345', 'Kim', 'Elec. Eng.', '80000');

create table department2
	(dept_name varchar(20), 
	 building varchar(15), 
	 budget numeric(12,2) check (budget > 0),
	 primary key (dept_name)
	);
    
create table course2
	(course_id varchar(8), 
	 title varchar(50), 
	 dept_name varchar(20),
	 credits numeric(2,0) check (credits > 0),
	 primary key (course_id),
	 foreign key (dept_name) references department2 (dept_name) on delete set null
	);
    
create table instructor2 (id varchar(5), 
	 name varchar(20) not null, 
	 dept_name varchar(20), 
	 salary numeric(8,2) check (salary > 29000),
	 primary key (ID),
	 foreign key (dept_name) references department2 (dept_name) on delete set null
	); 

insert into department2 values ('Biology', 'Watson', '90000');
insert into department2 values ('Comp. Sci.', 'Taylor', '100000');
insert into department2 values ('Elec. Eng.', 'Taylor', '85000');
insert into department2 values ('Finance', 'Painter', '120000');
insert into department2 values ('History', 'Painter', '50000');
insert into department2 values ('Music', 'Packard', '80000');
insert into department2 values ('Physics', 'Watson', '70000');
insert into course2 values ('BIO-101', 'Intro. to Biology', 'Biology', '4');
insert into course2 values ('BIO-301', 'Genetics', 'Biology', '4');
insert into course2 values ('BIO-399', 'Computational Biology', 'Biology', '3');
insert into course2 values ('CS-101', 'Intro. to Computer Science', 'Comp. Sci.', '4');
insert into course2 values ('CS-190', 'Game Design', 'Comp. Sci.', '4');
insert into course2 values ('CS-315', 'Robotics', 'Comp. Sci.', '3');
insert into course2 values ('CS-319', 'Image Processing', 'Comp. Sci.', '3');
insert into course2 values ('CS-347', 'Database System Concepts', 'Comp. Sci.', '3');
insert into course2 values ('EE-181', 'Intro. to Digital Systems', 'Elec. Eng.', '3');
insert into course2 values ('FIN-201', 'Investment Banking', 'Finance', '3');
insert into course2 values ('HIS-351', 'World History', 'History', '3');
insert into course2 values ('MU-199', 'Music Video Production', 'Music', '3');
insert into course2 values ('PHY-101', 'Physical Principles', 'Physics', '4');
insert into instructor2 values ('10101', 'Srinivasan', 'Comp. Sci.', '65000');
insert into instructor2 values ('12121', 'Wu', 'Finance', '90000');
insert into instructor2 values ('15151', 'Mozart', 'Music', '40000');
insert into instructor2 values ('22222', 'Einstein', 'Physics', '95000');
insert into instructor2 values ('32343', 'El Said', 'History', '60000');
insert into instructor2 values ('33456', 'Gold', 'Physics', '87000');
insert into instructor2 values ('45565', 'Katz', 'Comp. Sci.', '75000');
insert into instructor2 values ('58583', 'Califieri', 'History', '62000');
insert into instructor2 values ('76543', 'Singh', 'Finance', '80000');
insert into instructor2 values ('76766', 'Crick', 'Biology', '72000');
insert into instructor2 values ('83821', 'Brandt', 'Comp. Sci.', '92000');
insert into instructor2 values ('98345', 'Kim', 'Elec. Eng.', '80000');

create table department3
	(dept_name varchar(20), 
	 building varchar(15), 
	 budget numeric(12,2) check (budget > 0),
	 primary key (dept_name)
	);
    
create table course3
	(course_id varchar(8), 
	 title varchar(50), 
	 dept_name varchar(20),
	 credits numeric(2,0) check (credits > 0),
	 primary key (course_id),
	 foreign key (dept_name) references department3 (dept_name) on delete cascade 
	);
    
create table instructor3 (id varchar(5), 
	 name varchar(20) not null, 
	 dept_name varchar(20), 
	 salary numeric(8,2) check (salary > 29000),
	 primary key (ID),
	 foreign key (dept_name) references department3 (dept_name) on delete cascade 
	); 
 

insert into department3 values ('Biology', 'Watson', '90000');
insert into department3 values ('Comp. Sci.', 'Taylor', '100000');
insert into department3 values ('Elec. Eng.', 'Taylor', '85000');
insert into department3 values ('Finance', 'Painter', '120000');
insert into department3 values ('History', 'Painter', '50000');
insert into department3 values ('Music', 'Packard', '80000');
insert into department3 values ('Physics', 'Watson', '70000');   
insert into course3 values ('BIO-101', 'Intro. to Biology', 'Biology', '4');
insert into course3 values ('BIO-301', 'Genetics', 'Biology', '4');
insert into course3 values ('BIO-399', 'Computational Biology', 'Biology', '3');
insert into course3 values ('CS-101', 'Intro. to Computer Science', 'Comp. Sci.', '4');
insert into course3 values ('CS-190', 'Game Design', 'Comp. Sci.', '4');
insert into course3 values ('CS-315', 'Robotics', 'Comp. Sci.', '3');
insert into course3 values ('CS-319', 'Image Processing', 'Comp. Sci.', '3');
insert into course3 values ('CS-347', 'Database System Concepts', 'Comp. Sci.', '3');
insert into course3 values ('EE-181', 'Intro. to Digital Systems', 'Elec. Eng.', '3');
insert into course3 values ('FIN-201', 'Investment Banking', 'Finance', '3');
insert into course3 values ('HIS-351', 'World History', 'History', '3');
insert into course3 values ('MU-199', 'Music Video Production', 'Music', '3');
insert into course3 values ('PHY-101', 'Physical Principles', 'Physics', '4');
insert into instructor3 values ('10101', 'Srinivasan', 'Comp. Sci.', '65000');
insert into instructor3 values ('12121', 'Wu', 'Finance', '90000');
insert into instructor3 values ('15151', 'Mozart', 'Music', '40000');
insert into instructor3 values ('22222', 'Einstein', 'Physics', '95000');
insert into instructor3 values ('32343', 'El Said', 'History', '60000');
insert into instructor3 values ('33456', 'Gold', 'Physics', '87000');
insert into instructor3 values ('45565', 'Katz', 'Comp. Sci.', '75000');
insert into instructor3 values ('58583', 'Califieri', 'History', '62000');
insert into instructor3 values ('76543', 'Singh', 'Finance', '80000');
insert into instructor3 values ('76766', 'Crick', 'Biology', '72000');
insert into instructor3 values ('83821', 'Brandt', 'Comp. Sci.', '92000');
insert into instructor3 values ('98345', 'Kim', 'Elec. Eng.', '80000');
