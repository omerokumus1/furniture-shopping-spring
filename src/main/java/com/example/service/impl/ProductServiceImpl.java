package com.example.service.impl;

import com.example.controller.Data;
import com.example.dto.DtoProduct;
import com.example.dto.DtoProductDetail;
import com.example.service.IProductService;
import com.example.service.model.ImageData;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Override
    public List<DtoProduct> getAllProducts() {
        return Data.products;
    }

    @Override
    public Optional<DtoProductDetail> getProductById(int id) {
        for (DtoProductDetail detail : Data.productDetails) {
            if (detail.getId() == id) {
                return Optional.of(detail);
            }
        }
        return Optional.empty();
    }

    @Override
    public ImageData getProductImage(String imageName) throws IOException {
        Resource resource = getImageResource(imageName);
        if (resource.exists()) {
            String contentType = resource.getURL().openConnection().getContentType();
            return new ImageData(contentType, resource.getInputStream().readAllBytes());
        }
        return null;
    }

    @Override
    public List<byte[]> getProductImages(int productId) {
        List<String> imageNames = Data.productDetails.stream()
                .filter(detail -> detail.getId() == productId)
                .findFirst()
                .map(DtoProductDetail::getImages)
                .orElse(List.of());
        return imageNames.stream()
                .map(imageName -> {
                    try {
                        Resource resource = getImageResource(imageName);
                        return resource.exists() ? resource.getInputStream().readAllBytes() : null;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }


    private Resource getImageResource(String imageName) throws MalformedURLException {
        Path path = Paths.get("src/main/resources/static/products").resolve(imageName).normalize();
        return new UrlResource(path.toUri());
    }
}
