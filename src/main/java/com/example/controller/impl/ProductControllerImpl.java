package com.example.controller.impl;

import com.example.controller.Data;
import com.example.controller.IProductController;
import com.example.dto.DtoProduct;
import com.example.dto.DtoProductDetail;
import com.example.service.IProductService;
import com.example.service.model.ImageData;
import com.example.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements IProductController {
    @Autowired
    private IProductService productService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DtoProductDetail> getProductDetailById(@PathVariable int id) {
        Optional<DtoProductDetail> productDetail = productService.getProductById(id);
        return productDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DtoProduct>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Override
    @GetMapping(value = "/images/{imageName}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String imageName) {
        try {
            ImageData imageData = productService.getProductImage(imageName);
            if (imageData != null) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, imageData.getContentType())
                        .body(imageData.getData());
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{productId}/images")
    public ResponseEntity<List<byte[]>> getProductImages(@PathVariable int productId) {
        try {
            List<byte[]> images = productService.getProductImages(productId);
            if (images.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
