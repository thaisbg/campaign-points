package com.thaisbg.campaignpoints.campaigns;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("campaigns")
@RequiredArgsConstructor
public class CampaignsController {

    private final CampaignsService service;

    @PostMapping
    public CampaignPhrase createNewCampaignPhrase(@RequestBody PhraseDTO campaignPhraseDTO) {
        return service.createNewCampaignPhrase(campaignPhraseDTO.getPhrase());
    }

    @GetMapping
    public List<CampaignPhrase> getAllCampaignPhrases() {
        return service.getAllCampaignPhrases();
    }

    @PutMapping("/{phraseId}")
    public CampaignPhrase modifyCampaignPhrase(@PathVariable String phraseId, @RequestBody PhraseDTO newPhrase) {
        return service.modifyCampaignPhrase(phraseId, newPhrase.getPhrase());
    }

}
