package com.omerokumus.feature.product.controller;

import com.omerokumus.feature.product.dto.DtoProduct;
import com.omerokumus.feature.product.dto.DtoProductDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductController {

    ResponseEntity<DtoProductDetail> getProductDetailById(Long id);

    ResponseEntity<List<DtoProduct>> getAllProducts();

    ResponseEntity<byte[]> getProductImage(String imageName);

    ResponseEntity<List<byte[]>> getProductImages(Long id);

}
