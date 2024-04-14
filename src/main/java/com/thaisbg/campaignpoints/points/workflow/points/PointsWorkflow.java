package com.thaisbg.campaignpoints.points.workflow.points;

import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PointsWorkflow {

    @WorkflowMethod
    void processTweetAndAssignPoints(Tweet tweet);

    @SignalMethod
    void processTweet(Tweet tweet);

}
