import os
import sys
import csv
import firebase_admin
import google.cloud
from firebase_admin import credentials, firestore

cred = credentials.Certificate("danddank-homework4-dc4235f0a935.json")
app = firebase_admin.initialize_app(cred)

store = firestore.client()
doc_ref = store.collection('DanddankNetflixshows')

file_name = "netflix_titles.csv"

with open(file_name, "r", encoding='latin-1') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    data = {}
    for row in csv_reader:
        data = {
            "show_id":row[0], "type":row[1], "title":row[2], "director":row[3], "cast":row[4], "country":row[5], "date_added":row[6], 
            "release_year":row[7], "rating":row[8], "duration":row[9], "listed_in":row[10], "description":row[11]
        }
        doc_ref.add(data)
print("success!")