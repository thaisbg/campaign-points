package com.thaisbg.campaignpoints.points.workflow;

import com.thaisbg.campaignpoints.campaigns.CampaignsService;
import com.thaisbg.campaignpoints.tweets.model.Tweet;

public class PointsWorkflowImpl implements PointsWorkflow {

    private CampaignsService campaignsService;

    @Override
    public void processTweetAndAssignPoints(Tweet tweet) {
        // verify current campaign phrase
        // check if tweet contains the phrase
        // if so, creates a record in the ScoreHistory table to "log" the event
        // updates Score of the user, increasing 10 points
    }
}
