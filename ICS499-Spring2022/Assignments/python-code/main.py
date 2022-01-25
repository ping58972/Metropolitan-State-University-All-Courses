import requests
import json
import codecs
string = "hello"
language = "English"
# URI = "http://indic-wp.thisisjava.com/api/getLogicalChars.php?string={string}&language={language}"
# URI = "https://indic-wp.thisisjava.com/api/getLength.php?string=hello&language=English"
URL = "https://indic-wp.thisisjava.com/api/getLength.php"
params = {"string": "hello", "language": "English"}

# h = requests.head(URI)
# header = h.headers
# con = header.get("content-type")
# print(con)

# h = requests.head('https://www.instagram.com/p/CJDxE7Yp5Oj/?__a=1')
# header = h.headers
# contentType = header.get('content-type')
# print(contentType)
# iso-8859-1
headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}
r = requests.get(url=URL, params=params, headers=headers)
print(type(r.text))
# ru = r.text.decode("iso-8859-1")
# v = "\xC4pple"
v = "Apple"
print(v)
print(v.encode("iso-8859-1"))
# print(v.decode("iso-8859-1"))
# bytes(v, 'iso-8859-1').decode('utf-8')
# v.decode("iso-8859-1")
# rj = r.json()
# print(type(rj))
# res = r.content
# decoded_data = r.text.encode().decode('iso-8859-1')
# data = json.loads(decoded_data)
# print(type(data))
# # res = r.text.encode().decode('utf-8-sig')
# rr = json.loads(res)
# print(type(rr))
# print(type(res))
# r.encoding = 'utf-8-sig'
# data = json.loads(r.text)
# print(type(data))
# try:
#     # res = json.loads(r.text)
#     res = eval(r.text)
#     print(res)
#     print(type(res))
# except:
#     print("jhs")
# rr = '['+r.text+']'
# s = '[{"response_code":200,"message":"Length Calculated","string":"hello","language":"English","data":5}]'
# print(s)
# ree = json.loads(s)
# print(type(ree))
