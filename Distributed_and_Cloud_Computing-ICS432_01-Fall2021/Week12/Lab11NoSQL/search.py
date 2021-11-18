import boto3
from boto3.dynamodb.conditions import Key

dynamodb_client = boto3.resource("dynamodb", region_name="us-east-1")

table = dynamodb_client.Table("pets")

petname = "abc"

response = table.delete_item(
    Key={
        'petname': petname
    }
)

# breed = "Russian Blue"
# res = table.query(
#     IndexName="breed_index",
#     KeyConditionExpression=Key('breed').eq(breed)
# )
# for item in res['Items']:
#     print(item)


# petname = "Puddles"
# res = table.query(
#     KeyConditionExpression=Key('petname').eq(petname),
#     ProjectionExpression="notablefeatures"
# )
# print(res)


# response = table.get_item(Key={"petname":"Bella"})

# print(response)

# res = table.scan()
# items = res['Items']
# for item in items:
#     print(item['petname']+"\t"+item['breed'])
