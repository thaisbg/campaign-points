package com.thaisbg.campaignpoints.campaigns;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CampaignPhrase {
    private String id;
    private String phrase;
    private LocalDateTime creation;
    private LocalDateTime alteration;
    private LocalDateTime expiration;
}
