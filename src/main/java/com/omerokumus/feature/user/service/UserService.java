package com.omerokumus.feature.user.service;


import com.omerokumus.feature.product.entity.ProductEntity;
import com.omerokumus.feature.product.repository.ProductRepository;
import com.omerokumus.feature.user.dto.FavoriteProductDto;
import com.omerokumus.feature.user.dto.UserDto;
import com.omerokumus.feature.user.dto.UserDtoRequest;
import com.omerokumus.feature.user.entity.UserEntity;
import com.omerokumus.feature.user.exception.NotFoundException;
import com.omerokumus.feature.user.exception.ProductAlreadyInFavoritesException;
import com.omerokumus.feature.user.exception.UserAlreadyExistsException;
import com.omerokumus.feature.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public UserDto getUser(Long userId) throws NotFoundException {
        Optional<UserEntity> userEntityOpt = userRepository.findById(userId);
        UserDto userDto = new UserDto();
        if (userEntityOpt.isPresent()) {
            BeanUtils.copyProperties(userEntityOpt.get(), userDto);
            return userDto;
        }
        throw new NotFoundException("User with id " + userId + " not found.");
    }


    public UserDto addUser(UserDtoRequest userDtoRequest) throws UserAlreadyExistsException {
        Optional<UserEntity> existingUser = userRepository.findByEmail(userDtoRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userDtoRequest.getEmail() + " already exists.");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDtoRequest, userEntity);
        UserEntity savedEntity = userRepository.save(userEntity);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(savedEntity, userDto);
        return userDto;
    }

    public List<FavoriteProductDto> getFavoriteProducts(Long userId) throws NotFoundException {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found."));

        List<FavoriteProductDto> favoriteProductDtos = new ArrayList<>();
        for (ProductEntity productEntity : userEntity.getFavoriteProducts()) {
            FavoriteProductDto favoriteProductDto = new FavoriteProductDto();
            BeanUtils.copyProperties(productEntity, favoriteProductDto);
            favoriteProductDtos.add(favoriteProductDto);
        }
        favoriteProductDtos.sort(Comparator.comparing(FavoriteProductDto::getName));
        return favoriteProductDtos;

    }

    @Transactional
    public FavoriteProductDto addFavoriteProduct(Long userId, Long productId) throws NotFoundException, ProductAlreadyInFavoritesException {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found."));

        Set<ProductEntity> favoriteProducts = userEntity.getFavoriteProducts();
        for (final ProductEntity product: favoriteProducts) {
            if (product.getId().equals(productId)) {
                throw new ProductAlreadyInFavoritesException("Product with id " + productId + " is already in favorites.");
            }
        }

        ProductEntity productEntity = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with id " + productId + " not found."));

        favoriteProducts.add(productEntity);
        userEntity.setFavoriteProducts(favoriteProducts);
        userRepository.save(userEntity);

        FavoriteProductDto favoriteProductDto = new FavoriteProductDto();
        BeanUtils.copyProperties(productEntity, favoriteProductDto);
        return favoriteProductDto;
    }

    @Transactional
    public FavoriteProductDto removeFavoriteProduct(Long userId, Long productId) throws NotFoundException {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found."));

        Set<ProductEntity> favoriteProducts = userEntity.getFavoriteProducts();
        ProductEntity productToRemove = null;
        for (final ProductEntity product: favoriteProducts) {
            if (product.getId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove == null) {
            throw new NotFoundException("Product with id " + productId + " not found in favorites.");
        }

        favoriteProducts.remove(productToRemove);
        userEntity.setFavoriteProducts(favoriteProducts);
        userRepository.save(userEntity);

        FavoriteProductDto favoriteProductDto = new FavoriteProductDto();
        BeanUtils.copyProperties(productToRemove, favoriteProductDto);
        return favoriteProductDto;
    }
}
