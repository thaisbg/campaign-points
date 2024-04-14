package com.thaisbg.campaignpoints.campaigns;

import com.surrealdb.driver.SyncSurrealDriver;
import com.surrealdb.driver.model.QueryResult;
import com.thaisbg.campaignpoints.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class CampaignsRepository {

    private final SyncSurrealDriver driver;

    public CampaignsRepository() {
        this.driver = DatabaseConnection.connectToSurrealDB();
    }

    private static final String TABLE = "campaigns";

    public CampaignPhrase createNewCampaignPhrase(CampaignPhrase campaignPhrase) {
        return driver.create(TABLE, campaignPhrase);
    }

    public List<CampaignPhrase> getAllCampaignPhrases() {
        return driver.select(TABLE, CampaignPhrase.class);
    }

    public CampaignPhrase getCampaignPhraseById(String phraseId) {
        return driver.select(phraseId, CampaignPhrase.class).getFirst();
    }

    public CampaignPhrase modifyCampaignPhrase(CampaignPhrase alteredCampaignPhrase) {
        return driver.update(alteredCampaignPhrase.getId(), alteredCampaignPhrase).getFirst();
    }

    public CampaignPhrase getCurrentCampaignPhrase() {
        List<QueryResult<CampaignPhrase>> resultSet = driver.query("SELECT * FROM campaigns ORDER BY creation DESC LIMIT 1;", null, CampaignPhrase.class);
        if (Objects.nonNull(resultSet)
                && !resultSet.isEmpty()
                && Objects.nonNull(resultSet.getFirst().getResult())
                && !resultSet.getFirst().getResult().isEmpty()) {
            return resultSet.getFirst().getResult().getFirst();
        }
        return null;
    }

}
