package com.fsse2401.Project.repository;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    List<CartItemEntity> findAllByUser(UserEntity userEntity);
    Optional<CartItemEntity> findByProductAndUser(ProductEntity productEntity, UserEntity userEntity);
}
