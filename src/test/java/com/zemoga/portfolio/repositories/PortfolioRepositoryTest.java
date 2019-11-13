package com.zemoga.portfolio.repositories;

import com.zemoga.portfolio.PortfolioApplication;
import com.zemoga.portfolio.domain.Portfolio;
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
class PortfolioRepositoryTest {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Test
    void readTest() {
        Portfolio portfolio = new Portfolio();
        portfolioRepository.saveAndFlush(portfolio);
        Portfolio portfolio2 = portfolioRepository.findById(portfolio.getPortfolioId()).orElse(null);
        assertAll(
                "Check we can read from database",
                () -> assertNotNull(portfolio),
                () -> assertNotNull(portfolio2),
                () -> assertEquals(portfolio.getPortfolioId(), portfolio2.getPortfolioId())
        );
    }

    @Test
    void updateTest() {
        // Create a new entry for portfolio
        String title = "My fancy title";
        String description = "My fancy description";
        String imageUrl = "http://some/url/image.jpg";
        Portfolio saved = portfolioRepository.saveAndFlush(Portfolio.builder()
                .description(description)
                .title(title)
                .imageURL(imageUrl)
                .build());

        assertAll(
                "Verify entity",
                () -> assertNotNull(saved),
                () -> assertEquals(description, saved.getDescription()),
                () -> assertEquals(title, saved.getTitle()),
                () -> assertEquals(imageUrl, saved.getImageURL())
        );

        // Update entry
        String title2 = "This is my brand new title";
        String description2 = "This is my brand new description";
        String imageUrl2 = "http://some/url/This_is_my_brand_new_image.jpg";
        saved.setTitle(title2);
        saved.setDescription(description2);
        saved.setImageURL(imageUrl2);

        Portfolio updated = portfolioRepository.saveAndFlush(saved);

        assertAll(
                "Verify entity got updated",
                () -> assertEquals(description2, updated.getDescription()),
                () -> assertEquals(title2, updated.getTitle()),
                () -> assertEquals(imageUrl2, updated.getImageURL())
        );
    }

    @Test
    @Transactional
    void findNextPorfolioIdTest() {
        // Check current max id
        Integer nextPorfolioId = portfolioRepository.findNextPortfolioId();
        assertNotNull(nextPorfolioId);
        assertTrue(nextPorfolioId > 0);

        // Create an entity with next id
        Portfolio saved = portfolioRepository.saveAndFlush(Portfolio.builder().build());
        assertEquals(nextPorfolioId, saved.getPortfolioId());
    }

}
