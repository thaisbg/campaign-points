package com.thaisbg.campaignpoints.campaigns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignsService {

    private final CampaignsRepository repository;

    public CampaignPhrase createNewCampaignPhrase(String phrase) {
        CampaignPhrase newPhrase = CampaignPhrase.builder()
                .phrase(phrase)
                .creation(LocalDateTime.now())
                .build();

        inactivateLatestCampaign();
        return repository.createNewCampaignPhrase(newPhrase);
    }

    private void inactivateLatestCampaign() {
        CampaignPhrase currentPhrase = repository.getCurrentCampaignPhrase();
        currentPhrase.setExpiration(LocalDateTime.now());
        repository.modifyCampaignPhrase(currentPhrase);
    }

    public List<CampaignPhrase> getAllCampaignPhrases() {
        return repository.getAllCampaignPhrases();
    }

    public CampaignPhrase modifyCampaignPhrase(String phraseId, String newPhrase) {
        CampaignPhrase campaignPhrase = repository.getCampaignPhraseById(phraseId);
        campaignPhrase.setPhrase(newPhrase);
        campaignPhrase.setAlteration(LocalDateTime.now());
        return repository.modifyCampaignPhrase(campaignPhrase);
        // todo iniciar workflow para corrigir pontuação passada
    }

    public CampaignPhrase getCurrentCampaignPhrase() {
        return repository.getCurrentCampaignPhrase();
    }
}
