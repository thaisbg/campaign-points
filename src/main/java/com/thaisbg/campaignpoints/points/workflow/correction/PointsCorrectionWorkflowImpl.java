package com.thaisbg.campaignpoints.points.workflow.correction;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.points.workflow.PointsService;
import com.thaisbg.campaignpoints.tweets.TweetsRepository;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.spring.boot.WorkflowImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "correction-tasks")
@Service
public class PointsCorrectionWorkflowImpl implements PointsCorrectionWorkflow {

    private final PointsService pointsService;
    private final TweetsRepository tweetsRepository;

    private static final Long POINTS = 10L;

    @Override
    public void correctPointsFromPastCampaign(CampaignPhrase campaignPhrase) {
        List<Tweet> tweetsFromCampaignPeriod = tweetsRepository.getTweetsFromCampaignPeriod(campaignPhrase.getCreation(),
                campaignPhrase.getExpiration());

        List<Tweet> tweetsWithPoints = tweetsFromCampaignPeriod
                .stream()
                .filter(tweet -> tweet.getPayload().contains(campaignPhrase.getPhrase()))
                .toList();

        tweetsWithPoints.forEach(tweet -> {
                    pointsService.persistEvent(tweet, campaignPhrase, POINTS);
                    pointsService.updateUserScore(tweet, POINTS);
        });
    }

    @Override
    public void signalPastCampaignPhraseCorrection(CampaignPhrase campaignPhrase) {

    }

}
