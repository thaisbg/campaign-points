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

    public Phrase createNewCampaignPhrase(Phrase phrase) {
        return driver.create(TABLE, phrase);
    }

    public List<Phrase> getAllCampaignPhrases() {
        return driver.select(TABLE, Phrase.class);
    }

    public Phrase modifyCampaignPhrase(String phraseId, String newPhrase) {
        Phrase phraseToModify = driver.select(phraseId, Phrase.class).getFirst();
        Phrase alteredPhrase = new Phrase(phraseToModify.getId(),
                newPhrase,
                phraseToModify.getCreation(),
                LocalDateTime.now(),
                phraseToModify.getExpiration());
        return driver.update(phraseId, alteredPhrase).getFirst();
    }
}
