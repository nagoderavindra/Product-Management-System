package com.ravindra.product_management.Service;

import com.ravindra.product_management.DTO.ProductDto;
import com.ravindra.product_management.Entity.Product;
import com.ravindra.product_management.Repository.CategoryRepository;
import com.ravindra.product_management.Repository.ProductRepository;
import com.ravindra.product_management.Repository.UserRepository;
import com.ravindra.product_management.ServiceImpl.ProductServiceImpl;
import com.ravindra.product_management.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepositor;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetProductById() {

        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Laptop");

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductDto result = productService.getProductById(1L);

        assertEquals("Laptop", result.getProductName());
        assertEquals(1L,result.getProductId());

        verify(productRepository).findById(1L);
    }

    @Test
    void testGetProductById_ProductNotFound() {

        // Arrange
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.getProductById(1L)
        );

        verify(productRepository).findById(1L);
    }
}
