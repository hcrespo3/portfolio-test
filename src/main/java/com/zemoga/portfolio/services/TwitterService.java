package com.zemoga.portfolio.services;

import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.List;

@Service
public class TwitterService {

    public List<Status> getTimeline(String twitterUserName, int count) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        return twitter.getUserTimeline(twitterUserName, new Paging(1, count));
    }

}
