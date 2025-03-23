package com.omerokumus.feature.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteProductDto {

    private Long id;

    private String name;

    private String mainImage;

    private double price;

    private String priceUnit;

    private Boolean isInCart = false;
}
