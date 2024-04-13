package com.thaisbg.campaignpoints.campaigns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignsService {

    private final CampaignsRepository repository;

    public Phrase createNewCampaignPhrase(Phrase phrase) {
        phrase.setCreation(LocalDateTime.now());
        return repository.createNewCampaignPhrase(phrase);
    }

    public List<Phrase> getAllCampaignPhrases() {
        return repository.getAllCampaignPhrases();
    }

    public Phrase modifyCampaignPhrase(String phraseId, String newPhrase) {
        return repository.modifyCampaignPhrase(phraseId, newPhrase);
    }
}
