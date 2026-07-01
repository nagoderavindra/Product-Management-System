package com.ravindra.product_management.Repository;

import com.ravindra.product_management.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category ,Long> {


    Optional<Category> findByCategaryName(String category);
}
