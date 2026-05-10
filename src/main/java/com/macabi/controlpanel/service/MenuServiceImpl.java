package com.macabi.controlpanel.service;

import com.macabi.controlpanel.dto.menu.MenuRequestDto;
import com.macabi.controlpanel.dto.menu.MenuResponseDto;
import com.macabi.controlpanel.dto.menu.MenuUpdateDto;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.MenuMapper;
import com.macabi.controlpanel.model.Meal;
import com.macabi.controlpanel.model.Menu;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.enums.TypeMenu;
import com.macabi.controlpanel.repository.MealRepository;
import com.macabi.controlpanel.repository.MenuRepository;
import com.macabi.controlpanel.repository.ProjectRepository;
import com.macabi.controlpanel.service.iservice.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    
    private static final String MENU_RESOURCE       = "Menu";
    private static final String PROJECT_RESOURCE    = "Project";
    private static final String ID_FIELD            = "id";
    private static final String MENU_NOT_FOUND_MSG  = "Menu not found for project %d, date %s and type %s";
    private static final String MEALS_NOT_FOUND_MSG = "Some meals were not found. Requested: %d, Found: %d";
    private final MenuRepository    menuRepository;
    private final ProjectRepository projectRepository;
    private final MealRepository    mealRepository;
    private final MenuMapper        menuMapper;
    

    @Override
    @Transactional(readOnly = true)
    public List<MenuResponseDto> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(menuMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public MenuResponseDto getMenuById(Long id) {
        var menu = findMenuById(id);
        return menuMapper.toResponseDto(menu);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MenuResponseDto> getMenusByProjectId(Long projectId) {
        validateProjectExists(projectId);
        return menuRepository.findByProjectId(projectId).stream()
                .map(menuMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MenuResponseDto> getActiveMenusByProjectId(Long projectId) {
        validateProjectExists(projectId);
        return menuRepository.findByProjectIdAndActive(projectId, true).stream()
                .map(menuMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public MenuResponseDto getMenuByProjectDateAndType(Long projectId, LocalDate date, TypeMenu typeMenu) {
        validateProjectExists(projectId);
        var menu = menuRepository.findByProjectIdAndDateAndTypeMenu(projectId, date, typeMenu)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MENU_NOT_FOUND_MSG, projectId, date, typeMenu)));
        return menuMapper.toResponseDto(menu);
    }
    
    @Override
    public MenuResponseDto createMenu(MenuRequestDto menuDto) {
        Project project = findProjectById(menuDto.getProjectId());
        List<Meal> meals = findMealsByIds(menuDto.getMealIds());
        var menu = menuMapper.toEntity(menuDto, project, meals);
        var savedMenu = menuRepository.save(menu);
        return menuMapper.toResponseDto(savedMenu);
    }
    

    @Override
    public MenuResponseDto updateMenu(Long id, MenuUpdateDto menuDto) {
        var menu = findMenuById(id);
        List<Meal> meals = findMealsByIds(menuDto.getMealIds());
        menuMapper.updateEntityFromDto(menuDto, menu, meals);
        var updatedMenu = menuRepository.save(menu);
        return menuMapper.toResponseDto(updatedMenu);
    }
    
    @Override
    public void deleteMenu(Long id) {
        var menu = findMenuById(id);
        menuRepository.delete(menu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuResponseDto> getMenusByTypeMenu(TypeMenu typeMenu) {
        return menuRepository.findByTypeMenu(typeMenu).stream()
                .map(menuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuResponseDto> getMenusByProjectIdAndTypeMenu(Long projectId, TypeMenu typeMenu) {
        validateProjectExists(projectId);
        return menuRepository.findByProjectIdAndTypeMenu(projectId, typeMenu).stream()
                .map(menuMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    // Metodos privados
    
    private Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MENU_RESOURCE, ID_FIELD, id));
    }
    
    private Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PROJECT_RESOURCE, ID_FIELD, id));
    }
    
    private void validateProjectExists(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException(PROJECT_RESOURCE, ID_FIELD, projectId);
        }
    }
    
    private List<Meal> findMealsByIds(List<Long> mealIds) {
        List<Meal> meals = mealRepository.findAllById(mealIds);
        if (meals.size() != mealIds.size()) {
            throw new ResourceNotFoundException(
                    String.format(MEALS_NOT_FOUND_MSG, mealIds.size(), meals.size()));
        }
        return meals;
    }
}
