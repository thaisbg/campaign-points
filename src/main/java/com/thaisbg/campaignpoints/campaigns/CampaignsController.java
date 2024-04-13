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
    public Phrase createNewCampaignPhrase(@RequestBody Phrase phrase) {
        return service.createNewCampaignPhrase(phrase);
    }

    @GetMapping
    public List<Phrase> getAllCampaignPhrases() {
        return service.getAllCampaignPhrases();
    }

}
