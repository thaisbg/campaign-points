package com.thaisbg.campaignpoints.points;

import com.surrealdb.driver.SyncSurrealDriver;
import com.surrealdb.driver.model.QueryResult;
import com.thaisbg.campaignpoints.DatabaseConnection;
import com.thaisbg.campaignpoints.points.model.Score;
import com.thaisbg.campaignpoints.points.model.ScoreHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PointsRepository {

    private final SyncSurrealDriver driver;

    public PointsRepository() {
        this.driver = DatabaseConnection.connectToSurrealDB();
    }

    private static final String TABLE_SCORE_HISTORY = "score_history";
    private static final String TABLE_SCORE = "score";

    public void createNewScoreHistoryEvent(ScoreHistory event) {
        driver.create(TABLE_SCORE_HISTORY, event);
    }

    public Score getScoreByUserId(String userId) {
        List<QueryResult<Score>> resultSet = driver.query("SELECT * FROM score WHERE userId = @userId LIMIT 1;",
                Map.of("userId", "user:"+userId),
                Score.class);
        return resultSet.getFirst().getResult().getFirst();
    }

    public void updateScore(Score score) {
        driver.update(score.getId(), score).getFirst();
    }

    public List<Score> getAllScores() {
        return driver.select(TABLE_SCORE, Score.class);
    }

}
