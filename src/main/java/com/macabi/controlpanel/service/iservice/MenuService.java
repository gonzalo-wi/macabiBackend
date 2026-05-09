package com.macabi.controlpanel.service.iservice;

import com.macabi.controlpanel.dto.menu.MenuRequestDto;
import com.macabi.controlpanel.dto.menu.MenuResponseDto;
import com.macabi.controlpanel.dto.menu.MenuUpdateDto;
import com.macabi.controlpanel.model.enums.TypeMenu;
import java.time.LocalDate;
import java.util.List;

public interface MenuService {
  
    List<MenuResponseDto> getAllMenus();
    MenuResponseDto getMenuById(Long id);
    List<MenuResponseDto> getMenusByProjectId(Long projectId);
    List<MenuResponseDto> getActiveMenusByProjectId(Long projectId);
    MenuResponseDto getMenuByProjectDateAndType(Long projectId, LocalDate date, TypeMenu typeMenu);
    MenuResponseDto createMenu(MenuRequestDto menuDto);
    MenuResponseDto updateMenu(Long id, MenuUpdateDto menuDto);
    void deleteMenu(Long id);
}

