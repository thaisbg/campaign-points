package com.thaisbg.campaignpoints.points.workflow.correction;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointsCorrectionWorkflowClient {

    private final WorkflowClient workflowClient;

    public void initiateCorrectionPointsWorkflow(CampaignPhrase campaignPhrase) {
        PointsCorrectionWorkflow workflow =
                workflowClient.newWorkflowStub(
                        PointsCorrectionWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("correction-tasks")
                                .setWorkflowId("correcting-points-" + campaignPhrase.getPhrase())
                                .build());
        WorkflowClient.start(workflow::correctPointsFromPastCampaign, campaignPhrase);
    }

}
