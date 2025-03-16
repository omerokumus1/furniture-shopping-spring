package com.omerokumus.feature.product.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "price_unit")
    private String priceUnit;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "image_names", columnDefinition = "JSON")
    private String imageNames;

    @Column(name = "color_codes", columnDefinition = "JSON")
    private String colorCodes;

    public ProductEntity(Long id, String name, double price, String priceUnit, String description, String mainImage, List<String> imageNames, List<String> colorCodes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceUnit = priceUnit;
        this.description = description;
        this.mainImage = mainImage;
        this.setImageNames(imageNames);
        this.setColorCodes(colorCodes);
    }


    public List<String> getColorCodes() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(colorCodes, new TypeReference<>() {
            });
        } catch (Exception e) {
            return null;
        }
    }

    public void setColorCodes(List<String> itemsList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.colorCodes = objectMapper.writeValueAsString(itemsList);
        } catch (Exception e) {
            this.colorCodes = "[]";
        }
    }

    public List<String> getImageNames() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(imageNames, new TypeReference<>() {
            });
        } catch (Exception e) {
            return null;
        }
    }

    public void setImageNames(List<String> itemsList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.imageNames = objectMapper.writeValueAsString(itemsList);
        } catch (Exception e) {
            this.imageNames = "[]";
        }
    }
}
