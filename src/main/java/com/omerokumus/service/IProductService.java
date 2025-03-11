package com.omerokumus.service;

import com.omerokumus.dto.DtoProduct;
import com.omerokumus.dto.DtoProductDetail;
import com.omerokumus.service.model.ImageData;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<DtoProduct> getAllProducts();

    Optional<DtoProductDetail> getProductById(Long id);

    ImageData getProductImage(String imageName) throws Exception;

    List<byte[]> getProductImages(Long productId);

}
