import json

with open("pets_data.json") as json_file:
    lines = json_file.readlines()
    for line in lines:
        jsonobject = json.loads(line)
        for key in jsonobject.keys():
            print(key, jsonobject[key])
    # print(jsonobject['lastname'])
    # print(jsonobject['city'])
    # print(jsonobject['number'])
