package com.zemoga.portfolio.services;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.dto.ModifyUserDto;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.helpers.PortfolioHelper;
import com.zemoga.portfolio.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    private PortfolioRepository portfolioRepository;

    /**
     * Get User Info by portfolio id
     *
     * @param idPortfolio
     * @return
     * @throws PortfolioNotFoundException
     */
    public Portfolio getPortfolio(Integer idPortfolio) throws PortfolioNotFoundException {
        Portfolio portfolio = portfolioRepository.findById(idPortfolio).orElse(null);

        if (portfolio == null) {
            throw new PortfolioNotFoundException(idPortfolio);
        }

        return portfolio;
    }

    /**
     * Updates user information by portfolio id
     *
     * @param portfolio
     * @return Portfolio
     * @throws PortfolioNotFoundException
     */
    public Portfolio updatePortfolio(Portfolio portfolio) throws PortfolioNotFoundException {

        if (portfolio == null || portfolio.getPortfolioId() == null || portfolio.getPortfolioId() <= 0) {
            throw new PortfolioNotFoundException();
        }

        // Check if portfolio exists
        Portfolio current = portfolioRepository.findById(portfolio.getPortfolioId()).orElse(null);
        if (current == null) {
            throw new PortfolioNotFoundException(portfolio.getPortfolioId());
        }

        // Update
        return portfolioRepository.saveAndFlush(portfolio);
    }

    @Autowired
    public void setPortfolioRepository(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }
}
