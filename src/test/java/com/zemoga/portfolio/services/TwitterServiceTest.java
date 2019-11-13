package com.zemoga.portfolio.services;

import com.zemoga.portfolio.PortfolioApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PortfolioApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class TwitterServiceTest {

    @Autowired
    TwitterService twitterService;

    @Test
    public void givenTimelineIsRequired_thenReturnTimeline() throws TwitterException {
        List<Status> tweets = twitterService.getTimeline("zemoga", 5);
        assertNotNull(tweets);
        assertEquals(5, tweets.size());
    }

}
