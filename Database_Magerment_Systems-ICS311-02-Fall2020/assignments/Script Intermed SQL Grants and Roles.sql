--------------------------------------------------
-- Create user1/ics311 with user1 login(not root)
-- Create user2/ics311 with user1 login (not root)
--------------------------------------------------

-------------------------------------
-- Using Root to grant privileges --
-------------------------------------
use university;

GRANT SELECT ON customer TO 'user1';
GRANT INSERT ON customer TO 'user1';
GRANT UPDATE ON customer TO 'user1';
GRANT DELETE ON customer TO 'user1';

GRANT SELECT ON customer TO 'user2';
GRANT INSERT ON customer TO 'user2';
GRANT UPDATE ON customer TO 'user2';
GRANT DELETE ON customer TO 'user2';

GRANT INDEX ON customer.* to 'user1';
GRANT ALL ON customer.* to user1;

-- REVOKE --
REVOKE SELECT ON customer FROM 'user1';
REVOKE INSERT ON customer FROM 'user1';
REVOKE UPDATE ON customer FROM 'user1';
REVOKE DELETE ON customer FROM 'user1';

REVOKE SELECT ON customer FROM 'user2';
REVOKE INSERT ON customer FROM 'user2';
REVOKE UPDATE ON customer FROM 'user2';
REVOKE DELETE ON customer FROM 'user2';

-- ROLE --
use university;
create role instructor;
grant select on takes to instructor;
grant instructor to user2;
grant select on student to instructor;

revoke instructor from user2;
drop role instructor;

-------------------------------------
-- Grantee user1 --
-------------------------------------
use university;
show grants;

select * from customer;

insert into customer values('10018', 'Goyal', 'Rajeev', Null, '612', '4815775');
update customer set cus_initial = 'M' where cus_code = '10014';
delete from customer where cus_code = '10018';


-------------------------------------
-- Grantee user2 --
-- Roles --
-------------------------------------
show grants;
select current_role();
set role instructor;

use university;
select * from takes;
select * from student;