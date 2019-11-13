package com.zemoga.portfolio.helpers;

import com.zemoga.portfolio.PortfolioApplication;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.dto.ModifyUserDto;
import com.zemoga.portfolio.domain.dto.UserInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PortfolioApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
class PortfolioHelperTest {

    @Test
    void portfolioToUserInfoDtoTest() {
        Portfolio portfolio = Portfolio.builder()
                .portfolioId(23)
                .description("This is my cool description")
                .imageURL("some/url/image.jpg")
                .title("This is my nice title")
                .build();
        UserInfoDto dto = PortfolioHelper.portfolioToUserInfoDto(portfolio);

        assertEquals(portfolio.getTitle(), dto.title);
        assertEquals(portfolio.getImageURL(), dto.imageUrl);
        assertEquals(portfolio.getDescription(), dto.description);
    }

    @Test
    void modifyUserDtoToPortfolio() {
        Integer portfolioId = 23;
        ModifyUserDto dto = ModifyUserDto.builder()
                .title("This is my nice title")
                .description("This is my cool description")
                .imageUrl("some/url/image.jpg")
                .build();
        Portfolio portfolio = PortfolioHelper.modifyUserDtoToPortfolio(portfolioId, dto);

        assertEquals(portfolioId, portfolio.getPortfolioId());
        assertEquals(dto.title, portfolio.getTitle());
        assertEquals(dto.description, portfolio.getDescription());
        assertEquals(dto.imageUrl, portfolio.getImageURL());
    }
}
