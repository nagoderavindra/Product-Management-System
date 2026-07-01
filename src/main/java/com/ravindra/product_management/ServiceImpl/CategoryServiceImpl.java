package com.ravindra.product_management.ServiceImpl;

import com.ravindra.product_management.DTO.CategoryDto;
import com.ravindra.product_management.Entity.Category;
import com.ravindra.product_management.Repository.CategoryRepository;
import com.ravindra.product_management.Service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category =  Category.builder()
                   .categaryName(categoryDto.getCategaryName())
                   .description(categoryDto.getDescription())
                   .build();
        Category saved =  categoryRepository.save(category);
        categoryDto.setCategaryId(saved.getCatagaryId());

        return categoryDto;
    }
}
