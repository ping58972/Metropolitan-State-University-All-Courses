/* 
Class Exercise 1
Write a trigger, each time a row is Inserted in the table Invoice_line, 
Insert a row in Invoice table. Assume that cus_code is 1001 
and inv_date is today’s date
Invoice(inv_number int, cus_code varchar(5), inv_date date)
Invoice_Line(inv_number int, prod_code varchar(5), line_units int)
*/

delimiter //
CREATE TRIGGER Inv_Line_Trig
AFTER INSERT ON Line
FOR EACH ROW 
BEGIN   
INSERT INTO Invoice VALUES (new.inv_number, '1001', '2018-10-03');
END;
// delimiter ;

/* 
Class Exercise 2
Write an AFTER Insert trigger for the following Employee table. 
Check Date of Birth and calculate age. 
Update AGE field with calculated value in Employee_Info table
Employee (Emp_ID int, DOB date)
Employee_Info (Emp_ID int, Fname char, Lname char, Age int)
Set variable Current_Date to CURDATE()
Substract DOB from Current_Date to find Age 
*/

delimiter //
CREATE TRIGGER Employee_Trig
AFTER INSERT ON Employee
FOR EACH ROW 
BEGIN 
      declare current_date date; 
      declare new_age date;
      set     @current_date = CURDATE();
      set     @new_age = current_date - new.DOB; 

      UPDATE  Employee_Info
      SET     Age = new_age
      WHERE   Emp_id = new.Emp_id;
END;
// delimiter ;


/* Class Exercise 3 */
delimiter //
create procedure prc_dept_budget(in b_perc int)
begin
       Insert into department values ('Chemistry', 'Hudson', 40000 + (40000 * b_perc/100));
end //
delimiter ;

select * from department;
call prc_dept_budget(10);

/* Class Exercise 4 */

delimiter $$
delimiter $$
CREATE PROCEDURE prc_ins_inst
    (IN X_ID VARCHAR(20), 
     IN X_NAME VARCHAR(20), 
     IN X_DEPTNAME VARCHAR(20), 
     IN X_SAL numeric(8,2))
BEGIN

DECLARE
    W_SALARY numeric(8,2);
    SET @W_SALARY = 0.0;
    
    IF X_DEPTNAME = 'Physics' THEN
        SET @W_SALARY = X_SAL * 1.2;
	ELSE 
        SET @W_SALARY = X_SAL;
    END IF;

SELECT X_DEPTNAME, @W_SALARY;

	INSERT INTO INSTRUCTOR
	VALUES 	(X_ID, X_NAME, X_DEPTNAME, @W_SALARY);
END $$
delimiter ;


CALL prc_ins_inst(20202, 'Hanson', 'Physics', 80000);
CALL prc_ins_inst(30303, 'Snyder', 'Music', 55000);

select * from instructor where id in (20202, 30303);
