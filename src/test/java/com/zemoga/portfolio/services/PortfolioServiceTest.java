package com.zemoga.portfolio.services;

import com.zemoga.portfolio.PortfolioApplication;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.repositories.PortfolioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PortfolioApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
@Transactional
class PortfolioServiceTest {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private PortfolioRepository portfolioRepository;

    private Portfolio portfolio;

    @BeforeEach
    void before() {
        // Create portfolio
        portfolio = Portfolio.builder()
                .title("My Title")
                .description("My description")
                .imageURL("my/url/file.png")
                .build();
        portfolioRepository.saveAndFlush(portfolio);
    }

    @Test
    void givenAPortfolioIsRequested_thenGetUserInfoDto() throws PortfolioNotFoundException {
        Portfolio fetched = portfolioService.getPortfolio(this.portfolio.getPortfolioId());
        assertEquals(portfolio.getTitle(), fetched.getTitle());
        assertEquals(portfolio.getDescription(), fetched.getDescription());
        assertEquals(portfolio.getImageURL(), fetched.getImageURL());
    }

    @Test
    void givenAPortfolioIsRequested_whenWrongPortfolioId_thenThrowException() {
        Integer nextId = portfolioRepository.findNextPortfolioId();
        try {
            portfolioService.getPortfolio(nextId);
            fail("PortfolioNotFoundException should be thrown");
        } catch (PortfolioNotFoundException e) {
            assertEquals(String.format("Portfolio with id %d was not found.", nextId), e.getMessage());
        }
    }

    @Test
    void givenAPortfolioUpdateIsRequested_thenUpdate() throws PortfolioNotFoundException {
        String title = "My brand new title";
        String description = "My brand new description";
        String imageUrl = "/my/brand/new/url.jpg";
        portfolio.setTitle(title);
        portfolio.setDescription(description);
        portfolio.setImageURL(imageUrl);

        Portfolio updated = portfolioService.updatePortfolio(portfolio);
        assertAll(
                "Assert all values are right",
                () -> assertEquals(title, updated.getTitle()),
                () -> assertEquals(description, updated.getDescription()),
                () -> assertEquals(imageUrl, updated.getImageURL())
        );
    }

    @Test
    void givenAPortfolioUpdateIsRequested_whenWrongPortfolioId_thenThrowException() {
        try {
            portfolioService.updatePortfolio(new Portfolio());
            fail("PortfolioNotFoundException should be thrown");
        } catch (Exception e) {
            assertEquals("The portfolio was not found.", e.getMessage());
        }
    }
}
