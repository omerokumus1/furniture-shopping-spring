package com.omerokumus.controller;

import com.omerokumus.dto.DtoProduct;
import com.omerokumus.dto.DtoProductDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductController {

    ResponseEntity<DtoProductDetail> getProductDetailById(int id);

    ResponseEntity<List<DtoProduct>> getAllProducts();

    ResponseEntity<byte[]> getProductImage(String imageName);

    ResponseEntity<List<byte[]>> getProductImages(int id);

}
