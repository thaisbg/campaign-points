package com.thaisbg.campaignpoints.tweets.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tweet {
    private String id;
    private String payload;
    private LocalDateTime timestamp;
    private String userId;
}
