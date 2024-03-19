package com.fsse2401.Project.service;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.CartItem.dominaObject.CartItemResponseData;
import com.fsse2401.Project.data.CartItem.dominaObject.Result;
import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    Result putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemResponseData> getUserCartItems(FirebaseUserData firebaseUserData);

    CartItemResponseData upDateCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData);

    Result deleteCartItemByPid(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> getAllCartItemByUser(UserEntity userEntity);
}
