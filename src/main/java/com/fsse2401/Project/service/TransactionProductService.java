package com.fsse2401.Project.service;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;

import java.util.List;

public interface TransactionProductService {
    TransactionEntity createNewTransactionProduct(List<CartItemEntity> cartItemEntityList, TransactionEntity transactionEntity);
}
