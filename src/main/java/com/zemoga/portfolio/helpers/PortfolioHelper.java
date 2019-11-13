package com.zemoga.portfolio.helpers;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.dto.ModifyUserDto;
import com.zemoga.portfolio.domain.dto.UserInfoDto;

public class PortfolioHelper {

    public static UserInfoDto portfolioToUserInfoDto(Portfolio portfolio) {
        return UserInfoDto.builder()
                .description(portfolio.getDescription())
                .title(portfolio.getTitle())
                .imageUrl(portfolio.getImageURL())
                .build();
    }

    public static Portfolio modifyUserDtoToPortfolio(Integer portfolioId, ModifyUserDto modifyUserDto) {
        return Portfolio.builder()
                .portfolioId(portfolioId)
                .title(modifyUserDto.title)
                .imageURL(modifyUserDto.imageUrl)
                .description(modifyUserDto.description)
                .build();
    }

}
