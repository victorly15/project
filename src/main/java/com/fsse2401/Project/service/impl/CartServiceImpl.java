package com.fsse2401.Project.service.impl;

import com.fsse2401.Project.data.CartItem.dominaObject.CartItemResponseData;
import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.CartItem.dominaObject.Result;
import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import com.fsse2401.Project.data.product.entity.ProductEntity;
import com.fsse2401.Project.repository.CartItemRepository;
import com.fsse2401.Project.service.CartItemService;
import com.fsse2401.Project.service.ProductService;
import com.fsse2401.Project.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartItemService {

    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Result putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        ProductEntity productEntity = productService.getEntityById(pid);

        if (productEntity.getStock() >= quantity && quantity > 0) {

            Optional<CartItemEntity> optionalCartItemEntity =
                    cartItemRepository.findByProductAndUser(productEntity, userEntity);
            CartItemEntity cartItemEntity;
            if (optionalCartItemEntity.isEmpty()){
                cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
            } else {
                cartItemEntity = optionalCartItemEntity.get();
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
            }
            cartItemRepository.save(cartItemEntity);

            return Result.SUCCESS;
        }
        return Result.FAIL;
    }


    @Override
    public List<CartItemResponseData> getUserCartItems(FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userEntity);
        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();

        for (CartItemEntity cartItemEntity : cartItemEntityList)
        {
            CartItemResponseData cartItemResponseData = new CartItemResponseData(cartItemEntity);
            cartItemResponseDataList.add(cartItemResponseData);
        }

        return cartItemResponseDataList;
    }

    @Override
    public CartItemResponseData upDateCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        ProductEntity productEntity = productService.getEntityById(pid);

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProductAndUser(productEntity, userEntity);
        CartItemEntity cartItemEntity;
        if (optionalCartItemEntity.isEmpty()){
            cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
        } else {
            cartItemEntity = optionalCartItemEntity.get();
            cartItemEntity.setQuantity(quantity);
        }
        return new CartItemResponseData(cartItemRepository.save(cartItemEntity));

    }

    @Override
    @Transactional
    public Result deleteCartItemByPid(FirebaseUserData firebaseUserData, Integer pid){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        ProductEntity productEntity = productService.getEntityById(pid);

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProductAndUser(productEntity, userEntity);
        if (optionalCartItemEntity.isEmpty()){
            return Result.FAIL;
        } else {
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();
            cartItemRepository.delete(cartItemEntity);
        }
        return Result.SUCCESS;
    }

    @Override
    public List<CartItemEntity> getAllCartItemByUser(UserEntity userEntity)
    {
        return  cartItemRepository.findAllByUser(userEntity);
    }
}
