package com.omerokumus.feature.user.service;


import com.omerokumus.feature.product.entity.ProductEntity;
import com.omerokumus.feature.user.dto.FavoriteProductDto;
import com.omerokumus.feature.user.dto.UserDto;
import com.omerokumus.feature.user.dto.UserDtoRequest;
import com.omerokumus.feature.user.entity.UserEntity;
import com.omerokumus.feature.user.exception.UserAlreadyExistsException;
import com.omerokumus.feature.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUser(Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        UserDto userDto = new UserDto();
        if (userEntity.isPresent()) {
            BeanUtils.copyProperties(userEntity, userDto);
            return userDto;
        }
        return null;
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

    public List<FavoriteProductDto> getFavoriteProducts(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            List<FavoriteProductDto> favoriteProductDtos = new ArrayList<>();
            for (ProductEntity productEntity : userEntity.getFavoriteProducts()) {
                FavoriteProductDto favoriteProductDto = new FavoriteProductDto();
                BeanUtils.copyProperties(productEntity, favoriteProductDto);
                favoriteProductDtos.add(favoriteProductDto);
            }
            favoriteProductDtos.sort(Comparator.comparing(FavoriteProductDto::getName));
            return favoriteProductDtos;
        }
        return null;
    }

    public FavoriteProductDto addFavoriteProduct(Long userId, Long productId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            for (ProductEntity product: userEntity.getFavoriteProducts()) {
                if (product.getId().equals(productId)) {
                    FavoriteProductDto favoriteProductDto = new FavoriteProductDto();
                    BeanUtils.copyProperties(product, favoriteProductDto);
                    return favoriteProductDto;
                }
            }
        }

        return null;
    }
}
