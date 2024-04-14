package com.thaisbg.campaignpoints.points.workflow.points;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.campaigns.CampaignsRepository;
import com.thaisbg.campaignpoints.points.workflow.PointsService;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.spring.boot.WorkflowImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "points-tasks")
@Service
public class PointsWorkflowImpl implements PointsWorkflow {

    private final CampaignsRepository campaignsRepository;
    private final PointsService pointsService;

    private static final Long POINTS = 10L;

    @Override
    public void processTweetAndAssignPoints(Tweet tweet) {
        CampaignPhrase currentPhrase = campaignsRepository.getCurrentCampaignPhrase();
        if (tweet.getPayload().contains(currentPhrase.getPhrase())) {
            pointsService.persistEvent(tweet, currentPhrase, POINTS);
            pointsService.updateUserScore(tweet, POINTS);
        }
    }

    @Override
    public void processTweet(Tweet tweet) {

    }

}
