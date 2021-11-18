import json
import boto3

dynamodb_client = boto3.resource("dynamodb", region_name="us-east-1")

table = dynamodb_client.Table("pets")


with open("pets_data.json") as json_file:
    lines = json_file.readlines()
    for line in lines:
        jsonobject = json.loads(line)
        table.put_item(
            Item=jsonobject
        )
