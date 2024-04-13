package com.thaisbg.campaignpoints.points.workflow;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.campaigns.CampaignsService;
import com.thaisbg.campaignpoints.points.PointsRepository;
import com.thaisbg.campaignpoints.points.model.Score;
import com.thaisbg.campaignpoints.points.model.ScoreHistory;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import io.temporal.spring.boot.WorkflowImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@WorkflowImpl(taskQueues = "points-tasks")
@Service
public class PointsWorkflowImpl implements PointsWorkflow {

    private final CampaignsService campaignsService;
    private final PointsRepository pointsRepository;

    private static final Long POINTS = 10L;

    @Override
    public void processTweetAndAssignPoints(Tweet tweet) {
        CampaignPhrase currentPhrase = campaignsService.getCurrentCampaignPhrase();
        if (tweet.getPayload().contains(currentPhrase.getPhrase())) {
            persistEvent(tweet, currentPhrase);
            updateUserScore(tweet);
        }
    }

    private void persistEvent(Tweet tweet, CampaignPhrase currentPhrase) {
        ScoreHistory event = ScoreHistory.builder()
                .tweetId(tweet.getId())
                .campaignId(currentPhrase.getId())
                .points(POINTS)
                .build();
        pointsRepository.createNewScoreHistoryEvent(event);
    }

    private void updateUserScore(Tweet tweet) {
        Score userScore = pointsRepository.getScoreByUserId(tweet.getUserId());
        if (Objects.nonNull(userScore)) {
            userScore.setScore(userScore.getScore() + POINTS);
            pointsRepository.updateScore(userScore);
            return;
        }
        Score newScore = Score.builder().userId(tweet.getUserId()).score(POINTS).build();
        pointsRepository.createScore(newScore);
    }

}
