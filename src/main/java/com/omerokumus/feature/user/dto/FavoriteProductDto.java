package com.omerokumus.feature.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteProductDto {

    private Long id;

    private String name;

    private String description;

    private String price;

    private String priceUnit;

    private String mainImage;

    private String imageNames;

    private String colorCodes;
}
