package com.omerokumus.feature.user.controller;

import com.omerokumus.feature.user.dto.FavoriteProductDto;
import com.omerokumus.feature.user.dto.UserDtoRequest;
import com.omerokumus.feature.user.dto.UserDto;
import com.omerokumus.feature.user.exception.NotFoundException;
import com.omerokumus.feature.user.exception.ProductAlreadyInFavoritesException;
import com.omerokumus.feature.user.exception.UserAlreadyExistsException;
import com.omerokumus.feature.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO add validations
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws NotFoundException {
        UserDto userDto = userService.getUser(userId);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/user-by-email/{userEmail}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable(name = "userEmail") String userEmail) throws NotFoundException {
        UserDto userDto = userService.getUserByEmail(userEmail);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDtoRequest userDtoRequest) throws UserAlreadyExistsException {
        UserDto userDto = userService.addUser(userDtoRequest);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{userId}/favorite-products")
    public ResponseEntity<List<FavoriteProductDto>> getFavoriteProducts(@PathVariable Long userId) throws NotFoundException {
        List<FavoriteProductDto> favoriteProducts = userService.getFavoriteProducts(userId);
        if (favoriteProducts != null) {
            return ResponseEntity.ok(favoriteProducts);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/add-favorite-product/{productId}")
    public ResponseEntity<FavoriteProductDto> addFavoriteProduct(@PathVariable Long userId, @PathVariable Long productId) throws NotFoundException, ProductAlreadyInFavoritesException {
        FavoriteProductDto favoriteProductDto = userService.addFavoriteProduct(userId, productId);
        if (favoriteProductDto != null) {
            return ResponseEntity.ok(favoriteProductDto);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{userId}/remove-favorite-product/{productId}")
    public ResponseEntity<FavoriteProductDto> removeFavoriteProduct(@PathVariable Long userId, @PathVariable Long productId) throws NotFoundException {
        FavoriteProductDto favoriteProductDto = userService.removeFavoriteProduct(userId, productId);
        if (favoriteProductDto != null) {
            return ResponseEntity.ok(favoriteProductDto);
        }
        return ResponseEntity.internalServerError().build();
    }



}
