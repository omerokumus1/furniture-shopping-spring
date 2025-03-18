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

    private String description;

    private double price;

    private String priceUnit;

    private String mainImage;

    private List<String> imageNames;

    private List<String> colorCodes;
}
