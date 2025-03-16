package com.omerokumus.feature.product.service;

import com.omerokumus.feature.product.dto.DtoProduct;
import com.omerokumus.feature.product.dto.DtoProductDetail;
import com.omerokumus.feature.product.service.model.ImageData;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<DtoProduct> getAllProducts();

    Optional<DtoProductDetail> getProductById(Long id);

    ImageData getProductImage(String imageName) throws Exception;

    List<byte[]> getProductImages(Long productId);

}
