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
    public Tweet createTweet(@RequestBody Tweet tweet) {
        return service.createTweet(tweet);
    }

    @GetMapping
    public List<Tweet> getAllTweets() {
        return service.getAllTweets();
    }


}
