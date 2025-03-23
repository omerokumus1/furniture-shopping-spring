package com.omerokumus.feature.product.service;

import com.omerokumus.feature.product.controller.DummyData;
import com.omerokumus.feature.product.dto.DtoProduct;
import com.omerokumus.feature.product.dto.DtoProductDetail;
import com.omerokumus.feature.product.entity.ProductEntity;
import com.omerokumus.feature.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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



    public void saveAll() {
        ProductEntity lamp = new ProductEntity(null, "Lamp", 12.99, "$", DummyData.lorem1, "lamp_white_1.webp", DummyData.lampImages, DummyData.lampColors);
        ProductEntity sofa = new ProductEntity(null, "Sofa", 29.99, "$", DummyData.lorem2, "sofa_white_1.webp", DummyData.sofaImages, DummyData.sofaColors);
        ProductEntity wardrobe = new ProductEntity(null, "Wardrobe", 49.99, "$", DummyData.lorem3, "wardrobe_white_1.webp", DummyData.wardrobeImages, DummyData.wardrobeColors);
        ProductEntity bookcase = new ProductEntity(null, "Bookcase", 39.99, "$", DummyData.lorem4, "bookcase_white_1.webp", DummyData.bookcaseImages, DummyData.bookcaseColors);
        productRepository.saveAll(List.of(lamp, sofa, wardrobe, bookcase));
    }
}
