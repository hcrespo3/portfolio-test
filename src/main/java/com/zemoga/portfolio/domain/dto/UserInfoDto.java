package com.zemoga.portfolio.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class UserInfoDto {

    @JsonProperty("image")
    public String imageUrl;

    @JsonProperty("profile_name")
    public String title;

    @JsonProperty("profile_description")
    public String description;

}
