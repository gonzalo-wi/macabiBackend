package com.macabi.controlpanel.controller;

import com.macabi.controlpanel.dto.menu.MenuRequestDto;
import com.macabi.controlpanel.dto.menu.MenuResponseDto;
import com.macabi.controlpanel.dto.menu.MenuUpdateDto;
import com.macabi.controlpanel.model.enums.TypeMenu;
import com.macabi.controlpanel.service.iservice.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
@Tag(name = "Menus", description = "Menu management endpoints")
public class MenuController {
    
    private final MenuService menuService;
    
    @GetMapping
    public ResponseEntity<List<MenuResponseDto>> getAllMenus() {
        List<MenuResponseDto> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MenuResponseDto> getMenuById(@PathVariable Long id) {
        MenuResponseDto menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MenuResponseDto>> getMenusByProjectId(@PathVariable Long projectId) {
        List<MenuResponseDto> menus = menuService.getMenusByProjectId(projectId);
        return ResponseEntity.ok(menus);
    }
    
    @GetMapping("/project/{projectId}/active")
    public ResponseEntity<List<MenuResponseDto>> getActiveMenusByProjectId(@PathVariable Long projectId) {
        List<MenuResponseDto> menus = menuService.getActiveMenusByProjectId(projectId);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/type/{typeMenu}")
    public ResponseEntity<List<MenuResponseDto>> getMenusByTypeMenu(@PathVariable TypeMenu typeMenu) {
        List<MenuResponseDto> menus = menuService.getMenusByTypeMenu(typeMenu);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/project/{projectId}/type/{typeMenu}")
    public ResponseEntity<List<MenuResponseDto>> getMenusByProjectIdAndTypeMenu(
            @PathVariable Long projectId,
            @PathVariable TypeMenu typeMenu) {
        List<MenuResponseDto> menus = menuService.getMenusByProjectIdAndTypeMenu(projectId, typeMenu);
        return ResponseEntity.ok(menus);
    }
    
    @GetMapping("/project/{projectId}/search")
    public ResponseEntity<MenuResponseDto> getMenuByProjectDateAndType(
            @PathVariable Long projectId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam TypeMenu typeMenu) {
        MenuResponseDto menu = menuService.getMenuByProjectDateAndType(projectId, date, typeMenu);
        return ResponseEntity.ok(menu);
    }
    
    @PostMapping
    public ResponseEntity<MenuResponseDto> createMenu(@Valid @RequestBody MenuRequestDto menuDto) {
        MenuResponseDto createdMenu = menuService.createMenu(menuDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MenuResponseDto> updateMenu(
            @PathVariable Long id,
            @Valid @RequestBody MenuUpdateDto menuDto) {
        MenuResponseDto updatedMenu = menuService.updateMenu(id, menuDto);
        return ResponseEntity.ok(updatedMenu);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
