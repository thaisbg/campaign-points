package com.thaisbg.campaignpoints.points.workflow.correction;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.points.model.ScoreHistory;
import com.thaisbg.campaignpoints.points.workflow.PointsService;
import com.thaisbg.campaignpoints.tweets.TweetsRepository;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.spring.boot.WorkflowImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "correction-tasks")
@Service
@Slf4j
public class PointsCorrectionWorkflowImpl implements PointsCorrectionWorkflow {

    private final PointsService pointsService;
    private final TweetsRepository tweetsRepository;

    private static final Long POINTS = 10L;

    @Override
    public void correctPointsFromPastCampaign(CampaignPhrase campaignPhrase) {
        log.info("Initializing PointsCorrectionWorkflow");
        List<Tweet> tweetsFromCampaignPeriod = tweetsRepository.getTweetsFromCampaignPeriod(campaignPhrase.getCreation(),
                campaignPhrase.getExpiration());

        if (Objects.isNull(tweetsFromCampaignPeriod)) return;

        log.info("Found {} tweets that match the period of the campaign that was altered.", tweetsFromCampaignPeriod.size());
        List<Tweet> tweetsWithPoints = tweetsFromCampaignPeriod
                .stream()
                .filter(tweet -> tweet.getPayload().contains(campaignPhrase.getPhrase()))
                .toList();
        log.info("Found {} tweets that match the adjusted campaign phrase.", tweetsWithPoints.size());

        tweetsWithPoints.forEach(tweet -> {
                    log.info("Tweet {} from user {} matches the adjusted campaign phrase.", tweet.getUserId(), tweet.getUserId());
                    ScoreHistory createdScoreHistory = pointsService.persistEvent(tweet, campaignPhrase, POINTS);
                    log.info("Generated points to matching tweet: {}", Objects.nonNull(createdScoreHistory));
                    if (Objects.nonNull(createdScoreHistory)) {
                        pointsService.updateUserScore(tweet, POINTS);
                        log.info("Points successfully given to {}.", tweet.getUserId());
                    }
        });
    }

}
