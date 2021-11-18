import boto3

dynamodb_client = boto3.resource("dynamodb", region_name="us-east-1")

dynamodb_client.create_table(
    TableName="pets",
    KeySchema=[
        {
            "AttributeName": "petname",
            "KeyType": "HASH"
        }
    ],
    AttributeDefinitions=[
        {
            "AttributeName": "petname",
            "AttributeType": "S"
        }
    ],
    ProvisionedThroughput={
        "ReadCapacityUnits": 1,
        "WriteCapacityUnits": 1
    }
)
