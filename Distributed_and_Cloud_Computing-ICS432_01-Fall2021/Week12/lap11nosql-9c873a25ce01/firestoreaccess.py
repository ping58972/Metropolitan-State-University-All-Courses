import firebase_admin
import google.cloud
from firebase_admin import credentials, firestore

cred = credentials.Certificate("lap11nosql-9c873a25ce01.json")
app = firebase_admin.initialize_app(cred)

store = firestore.client()

doc_ref = store.collection(u'DanddankStudents').where("department", "==", "csc")

docs = doc_ref.get()

for doc in docs:
    print(u'Doc Data:{}'.format(doc.to_dict()))




# data = {
#     u'department': u'science',
#     u'lastname': u'iiii',
#     u'firstname': u'nnnn',
#     u'age': 30
# }
# store.collection(u'DanddankStudents').document(u'newdoc').set(data)

# doc_ref = store.collection(u'DanddankStudents').limit(5)

# docs = doc_ref.get()

# for doc in docs:
#     print(u'Doc Data:{}'.format(doc.to_dict()))