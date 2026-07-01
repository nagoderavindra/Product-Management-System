package com.ravindra.product_management.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long userId;
    private  String fullName;
    @Column(unique = true)
    private String email;
    private String password;
    private  String role;

}
