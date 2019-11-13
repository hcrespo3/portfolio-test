package com.zemoga.portfolio.exception;

public class PortfolioNotFoundException extends Exception {

    public PortfolioNotFoundException(Integer portfolioId) {
        super(String.format("Portfolio with id %d was not found.", portfolioId));
    }

    public PortfolioNotFoundException() {
        super("The portfolio was not found.");
    }
}
