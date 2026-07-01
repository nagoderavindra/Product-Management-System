package com.ravindra.product_management.Service;
import org.springframework.data.domain.Page;
import com.ravindra.product_management.DTO.ProductDto;

import java.util.List;

public interface ProductService
{
      ProductDto createProduct(ProductDto productDto);
        //List<ProductDto> getAllProduct();

    Page<ProductDto>getAllProduct(int page , int size);
        ProductDto getProductById(Long id);
        ProductDto updateProduct(Long id,ProductDto productDto);
        void  deleteProduct(Long id);
}
