package com.thaisbg.campaignpoints.points.workflow.points;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.campaigns.CampaignsRepository;
import com.thaisbg.campaignpoints.points.workflow.PointsService;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.spring.boot.WorkflowImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "points-tasks")
@Service
@Slf4j
public class PointsWorkflowImpl implements PointsWorkflow {

    private final CampaignsRepository campaignsRepository;
    private final PointsService pointsService;

    private static final Long POINTS = 10L;

    @Override
    public void processTweetAndAssignPoints(Tweet tweet) {
        log.info("Initializing PointsWorkflow");
        CampaignPhrase currentPhrase = campaignsRepository.getCurrentCampaignPhrase();
        if (Objects.nonNull(currentPhrase) && tweet.getPayload().contains(currentPhrase.getPhrase())) {
            log.info("Current phrase: {}", currentPhrase.getPhrase());
            log.info("Tweet {} from {} matches the current phrase. Giving points to user.", tweet.getId(), tweet.getUserId());
            pointsService.persistEvent(tweet, currentPhrase, POINTS);
            pointsService.updateUserScore(tweet, POINTS);
            log.info("Points successfully given to {}.", tweet.getUserId());
        }
    }
}
