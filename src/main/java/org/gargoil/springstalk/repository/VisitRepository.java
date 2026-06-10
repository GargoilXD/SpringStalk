package org.gargoil.springstalk.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class VisitRepository {
    private static final String COUNTER_ID = "home";

    private final DynamoDbClient dynamoDbClient;

    @Value("${aws.dynamodb.table-name}")
    private String tableName;

    public long increment() {
        UpdateItemResponse resp = dynamoDbClient.updateItem(
                UpdateItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("id", AttributeValue.builder().s(COUNTER_ID).build()))
                .updateExpression("ADD #c :one")
                .expressionAttributeNames(Map.of("#c", "count"))
                .expressionAttributeValues(Map.of(":one", AttributeValue.builder().n("1").build()))
                .returnValues(ReturnValue.UPDATED_NEW)
                .build()
        );

        return Long.parseLong(resp.attributes().get("count").n());
    }
}
