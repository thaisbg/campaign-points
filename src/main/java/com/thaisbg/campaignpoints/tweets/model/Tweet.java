package com.thaisbg.campaignpoints.tweets.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Tweet {
    private String id;
    private String payload;
    private LocalDateTime timestamp;
    private Long userId;
}
