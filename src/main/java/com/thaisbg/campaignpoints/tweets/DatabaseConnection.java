package com.thaisbg.campaignpoints.tweets;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {

    public static SyncSurrealDriver connectToSurrealDB() {
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("localhost", 8000, false);
        conn.connect(5);
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("root", "root");
        driver.use("campaign-points", "db-tweets");
        return driver;
    }

}
