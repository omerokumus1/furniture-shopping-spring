package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct {
    private int id;
    private String name;
    private double price;
    private String priceUnit;
    private String mainImage;
}
