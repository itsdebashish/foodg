package com.foodg.menu.service;

import com.foodg.menu.dto.MenuItemRequest;
import com.foodg.menu.dto.MenuItemResponse;
import com.foodg.menu.entity.MenuItem;
import com.foodg.menu.mapper.MenuMapper;
import com.foodg.menu.repository.MenuRepository;
import com.foodg.menu.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    public MenuItemResponse addToMenu(MenuItemRequest request) {
        MenuItem menuItem = menuMapper.toMenu(request);
        MenuItem savedMenu = menuRepository.save(menuItem);
        return menuMapper.fromMenu(savedMenu);
    }

    @Override
    public MenuItemResponse getMenuItem(Long id) {
        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        return menuMapper.fromMenu(menuItem);
    }

    @Override
    public MenuItemResponse updateMenuItem(MenuItemRequest request, Long id) {

        MenuItem existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        existingMenu.setName(request.getName());
        existingMenu.setDescription(request.getDescription());
        existingMenu.setPrice(request.getPrice());
        existingMenu.setAvailable(request.getAvailable());

        MenuItem updatedMenu = menuRepository.save(existingMenu);

        return menuMapper.fromMenu(updatedMenu);
    }

    @Override
    public MenuItemResponse deleteMenuItem(Long id) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        menuRepository.delete(menuItem);

        return menuMapper.fromMenu(menuItem);
    }

    @Override
    public Boolean checkAvailability(Long id) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        return menuItem.getAvailable();
    }

    @Override
    public Boolean toggleAvailability(Long id) {
        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        menuItem.setAvailable(!menuItem.getAvailable());
        menuRepository.save(menuItem);

        return menuItem.getAvailable();
    }
}