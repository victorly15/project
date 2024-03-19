package com.fsse2401.Project.service.impl;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import com.fsse2401.Project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;
import com.fsse2401.Project.repository.TransactionRepository;
import com.fsse2401.Project.service.CartItemService;
import com.fsse2401.Project.service.TransactionProductService;
import com.fsse2401.Project.service.TransactionService;
import com.fsse2401.Project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(UserService userService, CartItemService cartItemService, TransactionProductService transactionProductService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseData createNewTransaction(FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        List<CartItemEntity> cartItemEntityList = cartItemService.getAllCartItemByUser(userEntity);
        TransactionEntity transactionEntity = new TransactionEntity(userEntity, "PREPARE", cartItemEntityList);
        transactionRepository.save(transactionEntity);
//        for (CartItemEntity cartItemEntity : cartItemEntityList)
//        {
//            transactionEntity.getProductsHasInTransaction().add(new TransactionProductEntity(transactionEntity, cartItemEntity));
//        }
        return new TransactionResponseData(transactionProductService.createNewTransactionProduct(cartItemEntityList, transactionEntity));
    }
}
