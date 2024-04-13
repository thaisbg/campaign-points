package com.thaisbg.campaignpoints.points.model;

import lombok.Builder;

@Builder
public class ScoreHistory {
    private String id;
    private String tweetId;
    private String campaignId;
    private Long points;
}
