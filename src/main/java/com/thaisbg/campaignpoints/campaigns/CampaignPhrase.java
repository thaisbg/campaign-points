package com.thaisbg.campaignpoints.campaigns;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignPhrase {
    private String id;
    private String phrase;
    private LocalDateTime creation;
    private LocalDateTime alteration;
    private LocalDateTime expiration;
}
