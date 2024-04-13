package com.thaisbg.campaignpoints.tweets;

import com.thaisbg.campaignpoints.tweets.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetsService {

    private final TweetsRepository repository;

    public Tweet createTweet(Tweet tweet) {
        tweet.setTimestamp(LocalDateTime.now());
        return repository.createTweet(tweet);
    }

    public List<Tweet> getAllTweets() {
        return repository.getAllTweets();
    }
}
