package com.fsse2401.Project.service.impl;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;
import com.fsse2401.Project.repository.TransactionProductRepository;
import com.fsse2401.Project.service.TransactionProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {


    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionEntity createNewTransactionProduct(List<CartItemEntity> cartItemEntityList, TransactionEntity transactionEntity) {

        for (CartItemEntity cartItemEntity : cartItemEntityList)
        {
            transactionEntity.getProductsHasInTransaction().add(
                    transactionProductRepository.save(new TransactionProductEntity(transactionEntity, cartItemEntity)));
        }
        return transactionEntity;
    }
}
