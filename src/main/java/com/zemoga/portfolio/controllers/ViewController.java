package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.services.PortfolioService;
import com.zemoga.portfolio.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.TwitterException;

@Controller
@RequestMapping("/zemoga-portfolio")
public class ViewController {

    private PortfolioService portfolioService;
    private TwitterService twitterService;

    @GetMapping("/{portfolio-id}")
    public String index(@PathVariable("portfolio-id") Integer portfolioId, Model model)
            throws PortfolioNotFoundException, TwitterException {
        Portfolio portfolio = portfolioService.getPortfolio(portfolioId);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("timeline", twitterService.getTimeline(portfolio.getTwitterUserName(), 5));
        return "portfolio/show";
    }

    @Autowired
    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Autowired
    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }
}
