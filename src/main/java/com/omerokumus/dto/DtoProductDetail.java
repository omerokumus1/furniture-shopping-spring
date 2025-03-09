package com.omerokumus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProductDetail {
    private int id;
    private String name;
    private double price;
    private String priceUnit;
    private String description;
    private List<String> images;
    private List<String> colorCodes;
}
