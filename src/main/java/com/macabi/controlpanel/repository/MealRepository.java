package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    
    List<Meal> findByNameContainingIgnoreCase(String name);
    List<Meal> findByClasification(String clasification);
}
