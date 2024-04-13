package com.thaisbg.campaignpoints.tweets;

import com.surrealdb.driver.SyncSurrealDriver;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TweetsRepository {

    private final SyncSurrealDriver driver;

    public TweetsRepository() {
        this.driver = DatabaseConnection.connectToSurrealDB();
    }

    private static final String TABLE = "tweets";

    public Tweet createTweet(Tweet tweet) {
        return driver.create(TABLE, tweet);
    }

    public List<Tweet> getAllTweets() {
        return driver.select(TABLE, Tweet.class);
    }
}
