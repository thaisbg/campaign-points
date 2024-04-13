package com.thaisbg.campaignpoints.points.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Score {
    private String id;
    private String userId;
    private Long score;
}
