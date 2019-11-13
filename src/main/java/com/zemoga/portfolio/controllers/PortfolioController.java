package com.zemoga.portfolio.controllers;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.dto.ModifyUserDto;
import com.zemoga.portfolio.domain.dto.UserInfoDto;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.helpers.PortfolioHelper;
import com.zemoga.portfolio.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/zemoga-portfolio-api")
public class PortfolioController {

    private PortfolioService portfolioService;

    @GetMapping(name = "/user-info/{portfolio-id}", produces = {APPLICATION_JSON_VALUE})
    public UserInfoDto getUserInfo(@PathVariable("portfolio-id") Integer portfolioId) throws PortfolioNotFoundException {
        return PortfolioHelper.portfolioToUserInfoDto(portfolioService.getPortfolio(portfolioId));
    }

    @PatchMapping(name = "/user-info/{portfolio-id}", produces = {APPLICATION_JSON_VALUE})
    public UserInfoDto modifyUserInfo(
            @PathVariable("portfolio-id") Integer portfolioId, @RequestBody @Valid ModifyUserDto modifyUserDto) throws PortfolioNotFoundException {
        Portfolio portfolio = portfolioService.updatePortfolio(
                PortfolioHelper.modifyUserDtoToPortfolio(portfolioId, modifyUserDto));
        return PortfolioHelper.portfolioToUserInfoDto(portfolio);
    }

    @Autowired
    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
}
