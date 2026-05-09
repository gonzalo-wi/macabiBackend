package com.macabi.controlpanel.repository;

import com.macabi.controlpanel.model.ProjectProduct;
import com.macabi.controlpanel.model.Project;
import com.macabi.controlpanel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectProductRepository extends JpaRepository<ProjectProduct, Long> {
    
    List<ProjectProduct> findByProject(Project project);
    
    List<ProjectProduct> findByProjectId(Long projectId);
    
    List<ProjectProduct> findByProjectIdAndActive(Long projectId, boolean active);
    
    List<ProjectProduct> findByProduct(Product product);
    
    List<ProjectProduct> findByProductId(Long productId);
    
    Optional<ProjectProduct> findByProjectIdAndProductId(Long projectId, Long productId);
    
    @Query("SELECT SUM(pp.quantity) FROM ProjectProduct pp WHERE pp.product.id = :productId AND pp.active = true")
    Integer sumReservedQuantityByProductId(@Param("productId") Long productId);
}
