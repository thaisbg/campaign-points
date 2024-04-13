package com.thaisbg.campaignpoints.tweets;

import com.thaisbg.campaignpoints.points.workflow.PointsWorkflow;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetsService {

    private final TweetsRepository repository;
    private final PointsWorkflow pointsWorkflow;


    public Tweet createTweet(Tweet tweet) {
        tweet.setTimestamp(LocalDateTime.now());
        Tweet createdTweet = repository.createTweet(tweet);
        pointsWorkflow.processTweetAndAssignPoints(createdTweet);
        return createdTweet;
    }

    public List<Tweet> getAllTweets() {
        return repository.getAllTweets();
    }
}
