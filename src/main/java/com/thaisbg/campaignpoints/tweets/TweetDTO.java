package com.thaisbg.campaignpoints.tweets;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TweetDTO {
    private String payload;
    private String userId;
}
