package com.ravindra.product_management.Repository;

import com.ravindra.product_management.Entity.Category;
import com.ravindra.product_management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    boolean existsByEmail(String  Email);
    Optional<User> findByEmail(String email);
}
