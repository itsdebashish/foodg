package com.foodg.menu.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class MenuItemResponse {

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private Boolean available;

}