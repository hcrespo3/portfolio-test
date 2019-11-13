package com.zemoga.portfolio.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ModifyUserDto {

    @JsonProperty(value = "name", required = false)
    public String title;

    @JsonProperty(value = "image", required = false)
    public String imageUrl;

    @JsonProperty(value = "description", required = false)
    public String description;

}
