package com.fsse2401.Project.repository;

import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findByTidAndUser(Integer tid, UserEntity userEntity);
}
