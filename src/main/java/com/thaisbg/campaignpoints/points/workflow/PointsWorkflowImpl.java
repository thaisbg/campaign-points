package com.thaisbg.campaignpoints.points.workflow;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.campaigns.CampaignsService;
import com.thaisbg.campaignpoints.points.PointsRepository;
import com.thaisbg.campaignpoints.points.model.Score;
import com.thaisbg.campaignpoints.points.model.ScoreHistory;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointsWorkflowImpl implements PointsWorkflow {

    private final CampaignsService campaignsService;
    private final PointsRepository pointsRepository;

    private static final Integer POINTS = 10;

    @Override
    public void processTweetAndAssignPoints(Tweet tweet) {
        CampaignPhrase currentPhrase = campaignsService.getCurrentCampaignPhrase();
        if (tweet.getPayload().contains(currentPhrase.getPhrase())) {
            persistEvent(tweet, currentPhrase);
            Score userScore = pointsRepository.getScoreByUserId(tweet.getUserId());
            userScore.setScore(userScore.getScore() + POINTS);
            pointsRepository.updateScore(userScore);
        }
    }

    private void persistEvent(Tweet tweet, CampaignPhrase currentPhrase) {
        ScoreHistory event =  ScoreHistory.builder()
                .tweetId(tweet.getId())
                .campaignId(currentPhrase.getId())
                .points(POINTS)
                .build();
        pointsRepository.createNewScoreHistoryEvent(event);
    }
}
