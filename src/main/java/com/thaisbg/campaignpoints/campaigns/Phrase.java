package com.thaisbg.campaignpoints.campaigns;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Phrase {
    private String id;
    private String phrase;
    private LocalDateTime creation;
    private LocalDateTime expiration;
}
