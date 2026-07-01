package com.ravindra.product_management.Controller;

import com.ravindra.product_management.DTO.ProductDto;
import com.ravindra.product_management.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Tag(name = "Product Api",
              description = "Operation Releted to Product Management")

@RestController
@RequestMapping("/api/products")
public class ProductController {


      private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

     @Operation(summary = "Create new Product")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto)
    {
        return  ResponseEntity.ok(productService.createProduct(productDto));
    }

   // @GetMapping
   // public ResponseEntity<List<ProductDto>> getAllProduct(){

          //  return ResponseEntity.ok(productService.getAllProduct());
    //}
         @Operation(summary = "get all product with Pagination ")
          @GetMapping
          public ResponseEntity<Page<ProductDto>> getAllProduct(
                  @RequestParam(defaultValue = "0") int page,
                  @RequestParam(defaultValue = "3") int size) {

              return ResponseEntity.ok(
                      productService.getAllProduct(page, size));
          }
    @Operation(summary = "get Product by Id")
     @GetMapping("/{id}")
    public ResponseEntity <ProductDto >getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));


    }
      @Operation(summary = "update Existing Product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto){
            return ResponseEntity.ok(productService.updateProduct(id,productDto));
    }

     @Operation(summary = "delete Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }






}
