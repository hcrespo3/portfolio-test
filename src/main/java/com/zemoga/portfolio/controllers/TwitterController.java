package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

@RestController
@RequestMapping("/zemoga-portfolio-api")
public class TwitterController {

    private TwitterService twitterService;

    @GetMapping("/get-tweets/{twitter-username}/{count}")
    public List<Status> getTimeline(@PathVariable("twitter-username") String twitterUserName,
                                    @PathVariable("count") int count) throws TwitterException {
        return twitterService.getTimeline(twitterUserName, count);
    }

    @Autowired
    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }
}
