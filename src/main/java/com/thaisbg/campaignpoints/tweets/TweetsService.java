package com.thaisbg.campaignpoints.tweets;

import com.thaisbg.campaignpoints.points.workflow.points.PointsWorkflowClient;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetsService {

    private final TweetsRepository repository;
    private final PointsWorkflowClient pointsWorkflowClient;

    public Tweet createTweet(Tweet tweet) {
        tweet.setTimestamp(LocalDateTime.now());
        Tweet createdTweet = repository.createTweet(tweet);
        pointsWorkflowClient.initiatePointsWorkflow(createdTweet);
        return createdTweet;
    }

    public List<Tweet> getAllTweets() {
        return repository.getAllTweets();
    }

}
