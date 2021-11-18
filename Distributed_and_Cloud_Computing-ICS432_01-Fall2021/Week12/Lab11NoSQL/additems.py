import boto3

dynamodb_client = boto3.resource("dynamodb", region_name="us-east-1")

table = dynamodb_client.Table("pets")

table.put_item(
    Item={
        "breed": "Russian Blue",
        "petname": "Puddles",
        "color": "white",
        "size": "small"
    }
)
