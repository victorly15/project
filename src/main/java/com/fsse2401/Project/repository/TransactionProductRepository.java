package com.fsse2401.Project.repository;

import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
