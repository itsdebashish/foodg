package com.foodg.menu.controller;

import com.foodg.menu.dto.MenuItemRequest;
import com.foodg.menu.dto.MenuItemResponse;
import com.foodg.menu.entity.MenuItem;
import com.foodg.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuItemResponse> addToMenu(
            @Valid @RequestBody MenuItemRequest request) {

        MenuItemResponse response = menuService.addToMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getMenuItem(
            @PathVariable Long id) {

        MenuItemResponse response = menuService.getMenuItem(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequest request) {

        MenuItemResponse response = menuService.updateMenuItem(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuItemResponse> deleteMenuItem(
            @PathVariable Long id) {

        MenuItemResponse response = menuService.deleteMenuItem(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(
            @PathVariable Long id) {

        Boolean available = menuService.checkAvailability(id);
        return ResponseEntity.ok(available);
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Boolean> toggleAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.toggleAvailability(id));
    }
}