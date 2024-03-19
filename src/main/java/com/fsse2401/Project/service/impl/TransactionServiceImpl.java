package com.fsse2401.Project.service.impl;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.CartItem.dominaObject.Result;
import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import com.fsse2401.Project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.Project.exception.transactionException.TransactionNotFoundException;
import com.fsse2401.Project.repository.TransactionRepository;
import com.fsse2401.Project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final ProductService productService;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(UserService userService, CartItemService cartItemService, TransactionProductService transactionProductService, ProductService productService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.productService = productService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseData createNewTransaction(FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        List<CartItemEntity> cartItemEntityList = cartItemService.getAllCartItemByUser(userEntity);
        TransactionEntity transactionEntity = new TransactionEntity(userEntity, "PREPARE", cartItemEntityList);
        transactionRepository.save(transactionEntity);

        return new TransactionResponseData(transactionProductService.createNewTransactionProduct(cartItemEntityList, transactionEntity));
    }

    @Override
    public TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        return new TransactionResponseData(
                transactionRepository.findByTidAndUser(tid, userEntity).orElseThrow(TransactionNotFoundException::new));
    }

    @Override
    public Result payTransaction(FirebaseUserData firebaseUserData, Integer tid)
    {
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity transactionEntity = transactionRepository.findByTidAndUser(tid, userEntity).orElseThrow(TransactionNotFoundException::new);
        productService.reduceStock(transactionEntity);
        transactionEntity.setStatus("PROCESSING");
        transactionRepository.save(transactionEntity);
        return Result.SUCCESS;
    }

    @Override
    public TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid){

        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity transactionEntity = transactionRepository.findByTidAndUser(tid, userEntity).orElseThrow(TransactionNotFoundException::new);
        transactionEntity.setStatus("SUCCESS");
        cartItemService.emptyCartItemsforUser(userEntity);
        return new TransactionResponseData(transactionRepository.save(transactionEntity));

    }
}
