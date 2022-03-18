# pip install mysql-connector-python
import mysql.connector

# Establish the connection to mysql instance
mydb = mysql.connector.connect (
    host ="localhost",
    user="root",
    password=""
)

# create a database
mycursor = mydb.cursor()
create_db_sql = "CREATE DATABASE IF NOT EXISTS students2_db"
mycursor.execute(create_db_sql)

# connect to the database
mydb = mysql.connector.connect (
    host ="localhost",
    user="root",
    password="",
    database = "students2_db"
)

# create a table
create_table_sql = """CREATE TABLE IF NOT EXISTS students
                             (name VARCHAR(50),
                             email VARCHAR(100),
                             password VARCHAR(250))"""

mycursor = mydb.cursor()
mycursor.execute(create_table_sql)



# entry 1
insert_sql = "INSERT INTO students (name, email, password) VALUES "
name = "Siva1"
email = "Siva.Jasthi@ymail.com"
pwd = "abc123"
val = (name, email, pwd)
insert_sql = insert_sql + '("' + name + '","' + email + '","' + pwd + '")'
print (insert_sql)
mycursor.execute(insert_sql)

# entry 2
insert_sql = "INSERT INTO students (name, email, password) VALUES "
name = "Ramesh"
email = "Ramesh@gmail.com"
pwd = "123abc"
val = (name, email, pwd)
insert_sql = insert_sql + '("' + name + '","' + email + '","' + pwd + '")'
print (insert_sql)
mycursor.execute(insert_sql)


# getting the values from the database

mydb = mysql.connector.connect (
    host ="localhost",
    user="root",
    password="",
    database = "students2_db"
)

# fetching the data
cursor = mydb.cursor()
select_sql = "SELECT * FROM students"
cursor.execute(select_sql)
result = cursor.fetchall()

# Converting the data to plain text
print("Fetching the data from the database")
for row in result:
    name = row[0]
    email = row[1]
    pwd = row[2]
    print("name = " + name)
    print("email = " + email)
    print("encrypted pwd = " + pwd)


