package com.omerokumus.feature.user.controller;

import com.omerokumus.feature.user.dto.FavoriteProductDto;
import com.omerokumus.feature.user.dto.UserDtoRequest;
import com.omerokumus.feature.user.dto.UserDto;
import com.omerokumus.feature.user.exception.UserAlreadyExistsException;
import com.omerokumus.feature.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO add validations
// TODO add exception handling
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.getUser(userId);
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
    public ResponseEntity<List<FavoriteProductDto>> getFavoriteProducts(@PathVariable Long userId) {
        List<FavoriteProductDto> favoriteProducts = userService.getFavoriteProducts(userId);
        if (favoriteProducts != null) {
            return ResponseEntity.ok(favoriteProducts);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/add-favorite-product/{productId}")
    public ResponseEntity<FavoriteProductDto> addFavoriteProduct(@RequestParam Long userId, @RequestParam Long productId) {
        FavoriteProductDto favoriteProductDto = userService.addFavoriteProduct(userId, productId);
        if (favoriteProductDto != null) {
            return ResponseEntity.ok(favoriteProductDto);
        }
        return ResponseEntity.internalServerError().build();
    }


}
