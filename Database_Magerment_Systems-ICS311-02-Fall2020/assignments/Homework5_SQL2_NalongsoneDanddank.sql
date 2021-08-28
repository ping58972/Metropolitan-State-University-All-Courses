-- -- Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
-- -- Email: nalongsone.danddank@my.metrostate.edu
-- -- Homework #5
-- -- Question 1: Group by and Aggregates:
-- -- Write SQL statements to answer the following questions using Assignment 4’s schema 
-- -- (Customer-Invoice-Line-Product-Vendor). Make sure that your SQL script runs without any errors. 
-- -- Submit your answers in a .SQL file.

use nalongsonedanddank;

-- -- 1 (2 Points) - Find the count of distinct vendors that supplied products that are priced lower than 185? 
select count(distinct vend_code) 'Count Vendor' from product where prod_price < 185;

-- -- 2 (2 Points) - For each vendor, find their product that has the lowest product quantity. 
-- -- Your output should include vendor code, vendor name, product description and product quantity for each vendor. 
-- -- Hint: Use subquery to get minimum quantity
select V.vend_code, V.vend_name , P.prod_desc , P.prod_quant from vendor V natural join
(select  prod_quant, prod_desc, vend_code from product natural join
(select min(prod_quant) prod_quant, vend_code from product group by vend_code) P1) P ;

-- -- 3 (2 Points) - Find how many products are there in each invoice. 
-- -- The output should include invoice number and number of products in the invoice. 
select IL.inv_number 'Invoice Number', sum(IL.counts) 'Count Product'  
from ((select inv_number, count(counts) counts 
from (select inv_number, null counts from invoice) I group by I.inv_number)  
union (select inv_number, count(inv_number) counts from line group by inv_number) )IL 
group by IL.inv_number;

-- -- 4 (2 Points) - Find how many invoices are made by each customer. 
-- -- The output should be a list of cus_code and for each cus_code, the number of invoices made by this customer. 
select IL.cus_code 'Customer Code', sum(IL.counts) 'Count Customer'  
from ((select C.cus_code, count(counts) counts 
from (select cus_code, null counts from customer) C group by C.cus_code)  
union (select cus_code, count(I.cus_code) counts from invoice I group by I.cus_code) )IL 
group by IL.cus_code;

-- -- 5 (2 Points) - Find the total value for all products in the inventory. 
-- -- The total value in the inventory is the sum of product quantity * product price for 
-- -- all products listed in the product table. 
select sum(prod_price * prod_quant) 'Total Value' from product;

-- -- 6 (2 Points) - Find vendor code, vendor contact, and the number of products supplied by each vendor. 
select vend_code 'Vendor Code', vend_contact 'Vendor Contact', prod_quant 'Product Quantity' from 
(select vend_code, sum(prod_quant) prod_quant from product group by vend_code) P
 natural join (select vend_code, vend_contact from vendor) V;

-- -- 7 (2 Points) - Find product description, price, and vendor code for the cheapest (lowest price) product. 
select prod_desc, prod_price, vend_code from product
 where prod_price = (select min(prod_price) prod_price from product);

-- -- 8 (3 Points) - For each invoice, find the total price. 
-- -- The total invoice price is the sum of product price* line units for each product purchased in the invoice. 
select LP.inv_number 'Invoice Number', sum(LP.prod_price * LP.line_units) 'Total Price' 
from (select * from line natural join (select prod_price, prod_code from product) P 
where P.prod_code = prod_code) LP group by LP.inv_number;

-- -- 9 (3 Points) - Find how many products are bought by each customer. 
-- -- The output should be a list of cus_code and for each cus_code, the number of products purchased by this customer.  
-- -- A more complex query (if you want to try it), would be to list the name of the customer, along with the cus_code. 
select cus_code 'Customer Code', name 'Customer Name', line_units 'Total Products' 
from (select cus_code, sum(line_units) line_units 
from (select cus_code, sum(line_units) line_units 
from (select * from (select inv_number, sum(line_units) line_units 
from (select * from (select inv_number, sum(line_units) line_units 
from line group by inv_number)L
 union (select inv_number, 0 line_units from invoice)) LI
 group by inv_number) LII 
 natural join (select inv_number, cus_code from invoice) I) LIII
 group by cus_code
union select cus_code, 0 line_units from customer) LL 
group by cus_code) LIC natural join
(select cus_code, concat(cus_fname, ' ', cus_lname) name from customer) C;








