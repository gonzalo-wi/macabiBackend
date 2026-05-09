package com.macabi.controlpanel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.macabi.controlpanel.model.enums.TypeMenu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_menu", nullable = false)
    private TypeMenu typeMenu;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @ManyToMany
    @JoinTable(
        name = "menu_meals",
        joinColumns = @JoinColumn(name = "menu_id"),
        inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private List<Meal> meals = new ArrayList<>();
}
