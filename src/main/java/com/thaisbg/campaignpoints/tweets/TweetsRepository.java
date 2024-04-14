package com.thaisbg.campaignpoints.tweets;

import com.surrealdb.driver.SyncSurrealDriver;
import com.surrealdb.driver.model.QueryResult;
import com.thaisbg.campaignpoints.DatabaseConnection;
import com.thaisbg.campaignpoints.tweets.model.Tweet;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public List<Tweet> getTweetsFromCampaignPeriod(LocalDateTime start, LocalDateTime end) {
        List<QueryResult<Tweet>> resultSet = driver.query("SELECT * FROM tweets WHERE timestamp > start AND timestamp < end",
                Map.of("start", start.toString(), "end", end.toString()),
                Tweet.class);

        if (Objects.nonNull(resultSet)
                && !resultSet.isEmpty()
                && Objects.nonNull(resultSet.getFirst().getResult())
                && !resultSet.getFirst().getResult().isEmpty()) {
            return resultSet.getFirst().getResult();
        }
        return null;
    }
}
