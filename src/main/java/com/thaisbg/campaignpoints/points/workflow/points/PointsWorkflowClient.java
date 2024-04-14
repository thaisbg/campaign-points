package com.thaisbg.campaignpoints.points.workflow.points;

import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointsWorkflowClient {

    private final WorkflowClient workflowClient;

    public void initiatePointsWorkflow(Tweet tweet) {
        PointsWorkflow workflow =
                workflowClient.newWorkflowStub(
                        PointsWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("points-tasks")
                                .setWorkflowId("points-" + tweet.getUserId() + "-" + tweet.getId())
                                .build());
        WorkflowClient.start(workflow::processTweetAndAssignPoints, tweet);
    }

}
