package com.ravindra.product_management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Products")
public class Product
{

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long productId;
      private String productName;
      private String description;
      private Double price;
      private Integer quantity;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "category_id")
      private Category category;



      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "user_id")
      private User user;
}
