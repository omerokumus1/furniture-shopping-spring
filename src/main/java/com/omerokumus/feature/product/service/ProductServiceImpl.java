package com.omerokumus.feature.product.service;

import com.omerokumus.feature.product.controller.DummyData;
import com.omerokumus.feature.product.dto.DtoProduct;
import com.omerokumus.feature.product.dto.DtoProductDetail;
import com.omerokumus.feature.product.entity.ProductEntity;
import com.omerokumus.feature.product.repository.ProductRepository;
import com.omerokumus.feature.product.service.model.ImageData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<DtoProduct> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    DtoProduct dtoProduct = new DtoProduct();
                    BeanUtils.copyProperties(product, dtoProduct);
                    return dtoProduct;
                })
                .toList();
    }

    @Override
    public Optional<DtoProductDetail> getProductById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            DtoProductDetail dtoProductDetail = new DtoProductDetail();
            BeanUtils.copyProperties(productEntity.get(), dtoProductDetail);
            return Optional.of(dtoProductDetail);
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
    public List<byte[]> getProductImages(Long productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()) {
            return List.of();
        }

        List<String> imageNames = productEntity.get().getImageNames();
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

    public void saveAll() {
        ProductEntity lamp = new ProductEntity(null, "Lamp", 12.99, "$", DummyData.lorem1, "lamp_white_1.webp", DummyData.lampImages, DummyData.lampColors);
        ProductEntity sofa = new ProductEntity(null, "Sofa", 29.99, "$", DummyData.lorem2, "sofa_white_1.webp", DummyData.sofaImages, DummyData.sofaColors);
        ProductEntity wardrobe = new ProductEntity(null, "Wardrobe", 49.99, "$", DummyData.lorem3, "wardrobe_white_1.webp", DummyData.wardrobeImages, DummyData.wardrobeColors);
        ProductEntity bookcase = new ProductEntity(null, "Bookcase", 39.99, "$", DummyData.lorem4, "bookcase_white_1.webp", DummyData.bookcaseImages, DummyData.bookcaseColors);
        productRepository.saveAll(List.of(lamp, sofa, wardrobe, bookcase));
    }
}
