from flask import Flask
import os
import sys
import firebase_admin
import google.cloud
from firebase_admin import credentials, firestore
cred = credentials.Certificate("danddank-homework4-dc4235f0a935.json")
app = firebase_admin.initialize_app(cred)
store = firestore.client()
doc_ref = store.collection('DanddankNetflixshows')
app = Flask(__name__)

@app.route('/')
def netflix():
    return 'Welcome to Netflix Titles. You can list titles by Type or Release Year'

@app.route('/year/<year>')
def searchbyYear(year):
    doc_ref = store.collection(u'DanddankNetflixshows').where(u'release_year', u'==', year)
    docs = doc_ref.get()
    output = ""
    count = 1
    for doc in docs:
        output = output + str(count) + "\t" + format(doc.to_dict()) + "<br/>"
        count += 1
    return 'list of movies priduced of year {intype} is <br/> {list}'.format(intype=type, list=output)

@app.route('/type/<type>')
def searchbyType(type):
    doc_ref = store.collection(u'DanddankNetflixshows').where(u'type', u'==', type)
    docs = doc_ref.get()
    output = ""
    count = 1
    for doc in docs:
        output = output + str(count) + "\t" + format(doc.to_dict()) + "<br/>"
        count += 1
    return 'list of movies priduced of type {intype} is <br/> {list}'.format(intype=type, list=output)

@app.route('/country/<country>')
def searchbyCountry(country):
    doc_ref = store.collection(u'DanddankNetflixshows').where(u'country', u'==', country)
    docs = doc_ref.get()
    output = ""
    count = 1
    for doc in docs:
        output = output + str(count) + "\t" + format(doc.to_dict()) + "<br/>"
        count += 1
    return 'list of movies priduced of Country {intype} is <br/> {list}'.format(intype=type, list=output)

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8080, debug=True)