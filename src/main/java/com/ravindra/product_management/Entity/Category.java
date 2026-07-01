package com.ravindra.product_management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "catagaris")
public class Category {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long catagaryId;

      @Column(name = "categary_name",nullable = false,unique = true)
     private String categaryName;

     private String description;
}
