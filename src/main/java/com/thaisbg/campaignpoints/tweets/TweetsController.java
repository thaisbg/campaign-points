package com.thaisbg.campaignpoints.tweets;

import com.thaisbg.campaignpoints.tweets.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tweets")
@RequiredArgsConstructor
public class TweetsController {

    private final TweetsService service;

    @PostMapping
    public Tweet createTweet(@RequestBody TweetDTO tweetDTO) {
        Tweet newTweet = Tweet.builder()
                .payload(tweetDTO.getPayload())
                .userId(tweetDTO.getUserId())
                .build();

        return service.createTweet(newTweet);
    }

    @GetMapping
    public List<Tweet> getAllTweets() {
        return service.getAllTweets();
    }


}
