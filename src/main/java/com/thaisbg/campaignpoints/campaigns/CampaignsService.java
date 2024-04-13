package com.thaisbg.campaignpoints.campaigns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignsService {

    private final CampaignsRepository repository;

    public CampaignPhrase createNewCampaignPhrase(CampaignPhrase campaignPhrase) {
        campaignPhrase.setCreation(LocalDateTime.now());
        return repository.createNewCampaignPhrase(campaignPhrase);
    }

    public List<CampaignPhrase> getAllCampaignPhrases() {
        return repository.getAllCampaignPhrases();
    }

    public CampaignPhrase modifyCampaignPhrase(String phraseId, String newPhrase) {
        return repository.modifyCampaignPhrase(phraseId, newPhrase);
        // todo iniciar workflow para corrigir pontuação passada
    }

    public String getCurrentCampaignPhrase() {
        return repository.getCurrentCampaignPhrase();
    }
}
