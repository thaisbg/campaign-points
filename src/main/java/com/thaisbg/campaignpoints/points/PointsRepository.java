package com.thaisbg.campaignpoints.points;

import com.surrealdb.driver.SyncSurrealDriver;
import com.surrealdb.driver.model.QueryResult;
import com.thaisbg.campaignpoints.points.model.CountHistory;
import com.thaisbg.campaignpoints.points.model.Score;
import com.thaisbg.campaignpoints.points.model.ScoreHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class PointsRepository {

    private final SyncSurrealDriver driver;

    private static final String TABLE_SCORE_HISTORY = "score_history";
    private static final String TABLE_SCORE = "score";

    public ScoreHistory createNewScoreHistoryEvent(ScoreHistory event) {
        return driver.create(TABLE_SCORE_HISTORY, event);
    }

    public Score getScoreByUserId(String userId) {
        List<QueryResult<Score>> resultSet = driver.query("SELECT * FROM score WHERE userId = $userId LIMIT 1;",
                Map.of("userId", userId),
                Score.class);
        if (Objects.nonNull(resultSet) && !resultSet.isEmpty()
                && Objects.nonNull(resultSet.getFirst().getResult()) && !resultSet.getFirst().getResult().isEmpty()) {
            return resultSet.getFirst().getResult().getFirst();
        }
        return null;
    }

    public void updateScore(Score score) {
        driver.update(score.getId(), score).getFirst();
    }

    public List<Score> getAllScores() {
        return driver.select(TABLE_SCORE, Score.class);
    }

    public void createScore(Score userScore) {
        driver.create(TABLE_SCORE, userScore);
    }

    public Integer checkIfTweetAlreadyGeneratedPoints(String tweetId, String campaignId) {
        List<QueryResult<CountHistory>> resultSet =
                driver.query("SELECT count() FROM score_history WHERE tweetId = $tweetId AND campaignId = $campaignId GROUP BY count;",
                        Map.of("tweetId", tweetId, "campaignId", campaignId),
                        CountHistory.class);
        if (Objects.nonNull(resultSet) && !resultSet.isEmpty()
                && Objects.nonNull(resultSet.getFirst().getResult()) && !resultSet.getFirst().getResult().isEmpty()) {
            return resultSet.getFirst().getResult().getFirst().getCount();
        }
        return null;
    }
}
