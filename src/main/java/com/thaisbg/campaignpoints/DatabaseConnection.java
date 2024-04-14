package com.thaisbg.campaignpoints;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConnection {

    @Bean
    public SyncSurrealDriver connectionWithSurrealDB() {
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("localhost", 8000, false);
        conn.connect(5);
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("root", "root");
        driver.use("campaign-points", "db-campaign-points");
        return driver;
    }

}
