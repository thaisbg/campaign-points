package com.thaisbg.campaignpoints.campaigns;

import com.surrealdb.driver.SyncSurrealDriver;
import com.thaisbg.campaignpoints.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CampaignsRepository {

    private final SyncSurrealDriver driver;

    public CampaignsRepository() {
        this.driver = DatabaseConnection.connectToSurrealDB();
    }

    private static final String TABLE = "campaign";

    public Phrase createNewCampaignPhrase(Phrase phrase) {
        return driver.create(TABLE, phrase);
    }

    public List<Phrase> getAllCampaignPhrases() {
        return driver.select(TABLE, Phrase.class);
    }

}
