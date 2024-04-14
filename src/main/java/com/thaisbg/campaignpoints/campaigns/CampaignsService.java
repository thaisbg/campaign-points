package com.thaisbg.campaignpoints.campaigns;

import com.thaisbg.campaignpoints.points.workflow.correction.PointsCorrectionWorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CampaignsService {

    private final CampaignsRepository repository;
    private final PointsCorrectionWorkflowClient pointsCorrectionWorkflowClient;

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
        if (Objects.nonNull(currentPhrase)) {
            currentPhrase.setExpiration(LocalDateTime.now());
            repository.modifyCampaignPhrase(currentPhrase);
        }
    }

    public List<CampaignPhrase> getAllCampaignPhrases() {
        return repository.getAllCampaignPhrases();
    }

    public CampaignPhrase modifyCampaignPhrase(String phraseId, String newPhrase) {
        CampaignPhrase campaignPhrase = repository.getCampaignPhraseById(phraseId);
        campaignPhrase.setPhrase(newPhrase);
        campaignPhrase.setAlteration(LocalDateTime.now());
        CampaignPhrase updatedCampaign = repository.modifyCampaignPhrase(campaignPhrase);
        pointsCorrectionWorkflowClient.initiateCorrectionPointsWorkflow(updatedCampaign);
        return updatedCampaign;
    }

}
