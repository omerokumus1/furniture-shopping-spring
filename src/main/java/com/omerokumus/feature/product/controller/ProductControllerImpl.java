package com.omerokumus.feature.product.controller;

import com.omerokumus.feature.product.dto.DtoProduct;
import com.omerokumus.feature.product.dto.DtoProductDetail;
import com.omerokumus.feature.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductControllerImpl implements IProductController {
    @Autowired
    private IProductService productService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DtoProductDetail> getProductDetailById(@PathVariable Long id) {
        Optional<DtoProductDetail> productDetail = productService.getProductById(id);
        return productDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DtoProduct>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }



//    @GetMapping("/saveAll")
//    public void saveAll() {
//        ((ProductServiceImpl)productService).saveAll();
//    }
}
