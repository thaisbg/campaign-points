package com.thaisbg.campaignpoints.campaigns;

import com.surrealdb.driver.SyncSurrealDriver;
import com.thaisbg.campaignpoints.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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

    public CampaignPhrase modifyCampaignPhrase(String phraseId, String newPhrase) {
        CampaignPhrase campaignPhraseToModify = driver.select(phraseId, CampaignPhrase.class).getFirst();
        CampaignPhrase alteredCampaignPhrase = new CampaignPhrase(campaignPhraseToModify.getId(),
                newPhrase,
                campaignPhraseToModify.getCreation(),
                LocalDateTime.now(),
                campaignPhraseToModify.getExpiration());
        return driver.update(phraseId, alteredCampaignPhrase).getFirst();
    }
}
