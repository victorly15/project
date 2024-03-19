package com.fsse2401.Project.service;

import com.fsse2401.Project.data.CartItem.dominaObject.Result;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import com.fsse2401.Project.data.transaction.domainObject.response.TransactionResponseData;

public interface TransactionService {
    TransactionResponseData createNewTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);

    Result payTransaction(FirebaseUserData firebaseUserData, Integer tid);
}
