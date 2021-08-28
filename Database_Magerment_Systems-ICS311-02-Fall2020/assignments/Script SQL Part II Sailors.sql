create database sailor;

use sailor;

create table Sailors (
sid integer primary key,
sname varchar(30),
rating integer,
age integer);

insert into Sailors values
(22, 'dustin', 7, 45),
(31, 'lubber', 8, 55),
(58, 'rusty', 10, 35),
(28, 'Bob', 10, 35),
(41, 'Bob', 10, 20),
(44, 'guppy', 5, 50);

create table Boats (
bid integer primary key,
color varchar(30)
);

insert into Boats values
(101, 'Red'),
(103, 'Green');

create table Reserves (
sid integer,
bid integer,
day date,
primary key (sid, bid),
foreign key (sid) references Sailors (sid),
foreign key (bid) references Boats (bid)
);

insert into Reserves values
(22, 101, '1996-10-10'),
(58, 103, '1996-11-12'),
(58, 101, '1996-11-12');



select * from Sailors;
select * from reserves;
select * from boats;




-- UNION --
SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND B.color='red'
UNION
SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND B.color='green';

SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND (B.color='red' OR B.color='green');




-- INTERSECT --
SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND B.color='red'
INTERSECT
SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND B.color='green';

                       
SELECT  S.sid, S.sname
FROM    Sailors S, Boats B, Reserves R
WHERE   S.sid=R.sid AND R.bid=B.bid AND B.color='red'
AND     S.sid IN  (SELECT  S2.sid
				   FROM  Sailors S2, Boats B2, Reserves R2
				   WHERE  S2.sid=R2.sid AND R2.bid=B2.bid
				   AND  B2.color='green');
 
 
 
-- EXCEPT --
SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND B.color='red'
EXCEPT
SELECT  S.sid, S.sname, B.color
FROM  Sailors S, Boats B, Reserves R
WHERE  S.sid=R.sid AND R.bid=B.bid AND B.color='green';

                       
SELECT  S.sid, S.sname
FROM    Sailors S, Boats B, Reserves R
WHERE   S.sid=R.sid AND R.bid=B.bid AND B.color='red'
AND     S.sid NOT IN  (SELECT  S2.sid
				   FROM  Sailors S2, Boats B2, Reserves R2
				   WHERE  S2.sid=R2.sid AND R2.bid=B2.bid
				   AND  B2.color='green');




 use sailor;
-- AGGREGATE FUNCTIONS --
select * from sailors;
select count(*) from sailors;		
select avg(age) from sailors where rating =10;	
select avg(distinct age) from sailors where rating =10;	
select max(age) from sailors;	

select sname, max(age) from sailors;	-- Incorrect query	
select sname, age from sailors where age = (select max(s2.age) from sailors s2);




-- GROUP BY --
select * from sailors;

SELECT  S.rating,  MIN(S.age) AS minage
FROM  Sailors S
WHERE  S.age >= 18
GROUP BY  S.rating
HAVING  COUNT(*) > 0

