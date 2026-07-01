package com.ravindra.product_management.Repository;

import com.ravindra.product_management.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{
}
