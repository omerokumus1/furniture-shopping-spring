package com.omerokumus.feature.user.entity;

import com.omerokumus.feature.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "favoriteProducts")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String email;

    @Column(name = "profile_picture_name")
    private String profilePictureName;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> favoriteProducts = new HashSet<>();
}