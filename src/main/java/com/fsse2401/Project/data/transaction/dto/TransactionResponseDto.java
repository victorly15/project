package com.fsse2401.Project.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.Project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.Project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2401.Project.data.transactionProduct.dto.TransactionProductResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {

    private Integer tid;

    @JsonProperty("buyer_uid")
    private Integer buyerUid;

    private String dateTime;

    private String status;

    private BigDecimal total;

    @JsonProperty("items")
    List<TransactionProductResponseDto> productsHasInTransactionResponseDtoList = new ArrayList<>();

    public TransactionResponseDto(TransactionResponseData transactionResponseData) {
        this.tid = transactionResponseData.getTid();
        this.buyerUid = transactionResponseData.getBuyerUid();
        this.dateTime = transactionResponseData.getDateTime();
        this.status = transactionResponseData.getStatus();
        this.total = transactionResponseData.getTotal();

        for (TransactionProductResponseData transactionProductResponseData : transactionResponseData.getProductsHasInTransactionResponseList())
        {
            TransactionProductResponseDto transactionProductResponseDto = new TransactionProductResponseDto(transactionProductResponseData);
            productsHasInTransactionResponseDtoList.add(transactionProductResponseDto);
        }
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseDto> getProductsHasInTransactionResponseDtoList() {
        return productsHasInTransactionResponseDtoList;
    }

    public void setProductsHasInTransactionResponseDtoList(List<TransactionProductResponseDto> productsHasInTransactionResponseDtoList) {
        this.productsHasInTransactionResponseDtoList = productsHasInTransactionResponseDtoList;
    }
}
