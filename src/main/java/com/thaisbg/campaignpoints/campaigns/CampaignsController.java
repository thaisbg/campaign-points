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
    public CampaignPhrase createNewCampaignPhrase(@RequestBody CampaignPhrase campaignPhrase) {
        return service.createNewCampaignPhrase(campaignPhrase);
    }

    @GetMapping
    public List<CampaignPhrase> getAllCampaignPhrases() {
        return service.getAllCampaignPhrases();
    }

    @PutMapping("/{phraseId}")
    public CampaignPhrase modifyCampaignPhrase(@PathVariable String phraseId, @RequestBody PhraseChangeDTO newPhrase) {
        return service.modifyCampaignPhrase(phraseId, newPhrase.getNewPhrase());
    }

}
