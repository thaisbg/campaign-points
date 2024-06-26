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

    public ScoreHistory persistEvent(Tweet tweet, CampaignPhrase currentPhrase, Long points) {
        Integer tweetAlreadyGeneratedPoints = pointsRepository.checkIfTweetAlreadyGeneratedPoints(tweet.getId(), currentPhrase.getId());
        if (Objects.isNull(tweetAlreadyGeneratedPoints)) {
            ScoreHistory event = ScoreHistory.builder()
                    .tweetId(tweet.getId())
                    .campaignId(currentPhrase.getId())
                    .points(points)
                    .build();
            return pointsRepository.createNewScoreHistoryEvent(event);
        }
        return null;
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
