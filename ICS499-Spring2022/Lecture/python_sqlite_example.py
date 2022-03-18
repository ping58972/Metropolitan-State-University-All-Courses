import sqlite3

# clears the current db file or creates one if it doesn't exist
f = open("words_db.db","w")
f.close()

# establish the connection to the dataase
conn = sqlite3.connect('words_db.db')

#creates a cursor
c = conn.cursor()

#create a table for customers

create_sql = """CREATE TABLE words (
			                       id INTEGER NOT NULL primary key ,
			                       t_word text,
			                       word_len INTEGER 
		                           )"""
c.execute(create_sql)


#add some data to the table
some_words= [
	(1, 'cat' , 3),
    (2, 'ox', 2),
    (3, 'snake', 5),
    (4, 'పావురాయి', 4),
    (5, 'కోడిపుంజు', 4),
    (6, 'తెడ్డుమూతికొంగ', 6)]


c.executemany("INSERT INTO words VALUES (?,?,?)", some_words)

# fetch the data from the table
select_qry = 'SELECT * FROM words'
data = c.execute(select_qry)
print(data)
print(type(data))
rows = data.fetchall()
for row in rows:
    print(row)

conn.commit()

conn.close()
