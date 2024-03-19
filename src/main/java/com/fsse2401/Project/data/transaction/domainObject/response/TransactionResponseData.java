package com.fsse2401.Project.data.transaction.domainObject.response;

import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;
import com.fsse2401.Project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {
    private Integer tid;

    private Integer buyerUid;

    private String dateTime;

    private String status;

    private BigDecimal total;

    List<TransactionProductResponseData> productsHasInTransactionResponseList = new ArrayList<>();

    public TransactionResponseData(TransactionEntity transactionEntity) {
        this.tid = transactionEntity.getTid();;
        this.buyerUid = transactionEntity.getUser().getUid();
        this.dateTime = transactionEntity.getDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ss"));
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();

        for ( TransactionProductEntity transactionProductEntity : transactionEntity.getProductsHasInTransaction())
        {
            productsHasInTransactionResponseList.add(new TransactionProductResponseData(transactionProductEntity));
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

    public List<TransactionProductResponseData> getProductsHasInTransactionResponseList() {
        return productsHasInTransactionResponseList;
    }

    public void setProductsHasInTransactionResponseList(List<TransactionProductResponseData> productsHasInTransactionResponseList) {
        this.productsHasInTransactionResponseList = productsHasInTransactionResponseList;
    }
}
