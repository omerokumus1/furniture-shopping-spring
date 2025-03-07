package com.example.service;

import com.example.dto.DtoProduct;
import com.example.dto.DtoProductDetail;
import com.example.service.model.ImageData;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<DtoProduct> getAllProducts();

    Optional<DtoProductDetail> getProductById(int id);

    ImageData getProductImage(String imageName) throws Exception;

    List<byte[]> getProductImages(int productId);

}
