package com.fsse2401.Project.repository;

import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
