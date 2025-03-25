package com.omerokumus.feature.user.repository;

import com.omerokumus.feature.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT user FROM UserEntity user WHERE user.email = :email")
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM UserEntity u JOIN u.favoriteProducts p " +
            "WHERE u.id = :userId AND p.id = :productId")
    boolean isInFavoriteProducts(@Param("userId") Long userId, @Param("productId") Long productId);
}
