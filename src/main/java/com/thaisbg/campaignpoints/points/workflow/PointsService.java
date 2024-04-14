package com.thaisbg.campaignpoints.points.workflow;

import com.thaisbg.campaignpoints.campaigns.CampaignPhrase;
import com.thaisbg.campaignpoints.points.PointsRepository;
import com.thaisbg.campaignpoints.points.model.Score;
import com.thaisbg.campaignpoints.points.model.ScoreHistory;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PointsService {

    private final PointsRepository pointsRepository;

    public void persistEvent(Tweet tweet, CampaignPhrase currentPhrase, Long points) {
        ScoreHistory event = ScoreHistory.builder()
                .tweetId(tweet.getId())
                .campaignId(currentPhrase.getId())
                .points(points)
                .build();
        pointsRepository.createNewScoreHistoryEvent(event);
    }

    public void updateUserScore(Tweet tweet, Long points) {
        Score userScore = pointsRepository.getScoreByUserId(tweet.getUserId());
        if (Objects.nonNull(userScore)) {
            userScore.setScore(userScore.getScore() + points);
            pointsRepository.updateScore(userScore);
            return;
        }
        Score newScore = Score.builder().userId(tweet.getUserId()).score(points).build();
        pointsRepository.createScore(newScore);
    }

}
