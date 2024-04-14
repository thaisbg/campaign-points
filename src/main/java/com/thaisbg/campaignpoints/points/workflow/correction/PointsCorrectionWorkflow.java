package com.thaisbg.campaignpoints.points.workflow.correction;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PointsCorrectionWorkflow {

    @WorkflowMethod
    void correctPointsFromPastCampaign(CampaignPhrase campaignPhrase);

}
