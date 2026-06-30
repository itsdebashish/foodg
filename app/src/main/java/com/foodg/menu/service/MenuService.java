package com.foodg.menu.service;


import com.foodg.menu.dto.MenuItemRequest;
import com.foodg.menu.dto.MenuItemResponse;

public interface MenuService {
   public MenuItemResponse addToMenu(MenuItemRequest request);

   public MenuItemResponse getMenuItem(Long id);

   public MenuItemResponse updateMenuItem(MenuItemRequest request, Long id);

   public MenuItemResponse deleteMenuItem(Long id);

   public Boolean checkAvailability(Long id);

   public Boolean toggleAvailability(Long id);
}
