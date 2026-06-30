package com.foodg.menu.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItemRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private Boolean available;

    private String restaurantId;
}