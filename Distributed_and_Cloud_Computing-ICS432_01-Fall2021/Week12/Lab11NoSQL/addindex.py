import boto3

dynamodb_client = boto3.client("dynamodb", region_name="us-east-1")

res = dynamodb_client.update_table(
    AttributeDefinitions=[
        {
            "AttributeName": "breed",
            "AttributeType": "S"
        },
    ],
    TableName="pets",
    GlobalSecondaryIndexUpdates=[
        {
            'Create': {
                'IndexName': 'breed_index',
                'KeySchema': [
                    {
                        'AttributeName': 'breed',
                        'KeyType': 'HASH'
                    }
                ],
                'Projection': {
                    'ProjectionType': 'ALL'
                },
                'ProvisionedThroughput': {
                    'ReadCapacityUnits': 1,
                    'WriteCapacityUnits': 1
                }
            }
        }
    ],
)
print(res)
