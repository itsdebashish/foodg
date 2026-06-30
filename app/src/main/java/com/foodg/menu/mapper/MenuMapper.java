package com.foodg.menu.mapper;


import com.foodg.menu.dto.MenuItemRequest;
import com.foodg.menu.dto.MenuItemResponse;
import com.foodg.menu.entity.MenuItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuItem toMenu(MenuItemRequest item) {
       return MenuItem.builder().name(item.getName())
               .price(item.getPrice())
               .description(item.getDescription())
               .imageUrl(item.getImageUrl())
               .available(item.getAvailable())
               .build();
    }

    public MenuItemResponse fromMenu(MenuItem menuItem) {
       return MenuItemResponse.builder()
               .name(menuItem.getName())
               .description(menuItem.getDescription())
               .price(menuItem.getPrice())
               .imageUrl(menuItem.getImageUrl())
               .available(menuItem.getAvailable())
               .build();
    }

}