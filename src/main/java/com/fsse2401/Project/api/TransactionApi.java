package com.fsse2401.Project.api;

import com.fsse2401.Project.Util.JwtUtil;
import com.fsse2401.Project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.Project.data.transaction.dto.TransactionResponseDto;
import com.fsse2401.Project.service.TransactionService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {

    private  final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/prepare")
    public TransactionResponseDto createNewTransation(JwtAuthenticationToken jwtToken){

        TransactionResponseData transactionResponseData = transactionService.createNewTransaction(JwtUtil.getFirebaseUserData(jwtToken));
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(transactionResponseData);
        return transactionResponseDto;

    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(@PathVariable Integer tid, JwtAuthenticationToken jwtToken){
        TransactionResponseData transactionResponseData = transactionService.getTransactionByTid(JwtUtil.getFirebaseUserData(jwtToken), tid);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(transactionResponseData);
        return transactionResponseDto;


    }
}
