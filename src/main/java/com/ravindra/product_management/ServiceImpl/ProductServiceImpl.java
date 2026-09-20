package com.ravindra.product_management.ServiceImpl;

import com.ravindra.product_management.DTO.ProductDto;
import com.ravindra.product_management.Entity.Category;
import com.ravindra.product_management.Entity.Product;
import com.ravindra.product_management.Entity.User;
import com.ravindra.product_management.Repository.CategoryRepository;
import com.ravindra.product_management.Repository.ProductRepository;
import com.ravindra.product_management.Repository.UserRepository;
import com.ravindra.product_management.Service.ProductService;
import com.ravindra.product_management.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        log.info("Creating product: {}", productDto.getProductName());

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + productDto.getCategoryId()));

        Long userId = getLoggedInUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId));

        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(category);
        product.setUser(user);

        Product saved = productRepository.save(product);

        log.info("Product is created successfully with id: {}", saved.getProductId());

        return mapToDto(saved);
    }

    private ProductDto mapToDto(Product product) {

        ProductDto dto = new ProductDto();

        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getCatagaryId());
        }

        // userId intentionally not returned in response

        return dto;
    }

    @Override
    public Page<ProductDto> getAllProduct(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(this::mapToDto);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductDto getProductById(Long id) {

        log.info("Fetching product from DB with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    return new ResourceNotFoundException("Product not found");
                });

        log.info("Product found with id: {}", id);

        return mapToDto(product);
    }

    @Override
    @CachePut(value = "products", key = "#id")
    public ProductDto updateProduct(Long id, ProductDto productDto) {

        log.info("Updating product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        Long loggedInUserId = getLoggedInUserId();

        if (!product.getUser().getUserId().equals(loggedInUserId)) {
            throw new RuntimeException(
                    "You are not authorized to update this product");
        }

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(category);

        log.info("Updating product successfully with id: {}", id);

        return mapToDto(productRepository.save(product));
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {

        log.info("Deleting product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    return new ResourceNotFoundException("Product not found");
                });

        Long loggedInUserId = getLoggedInUserId();

        if (!product.getUser().getUserId().equals(loggedInUserId)) {
            throw new RuntimeException(
                    "You are not authorized to delete this product");
        }

        productRepository.delete(product);

        log.info("Product deleted successfully with id: {}", id);
    }

    private Long getLoggedInUserId() {

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        return (Long) authentication.getDetails();
    }
}