package com.fsse2401.Project.data.transaction.Entity;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "buyer_uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "transactionEntity")
    List<TransactionProductEntity> productsHasInTransaction = new ArrayList<>();

    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity userEntity, String status, List<CartItemEntity> cartItemEntityList) {
        this.user = userEntity;
        this.dateTime = LocalDateTime.now();
        this.status = status;

        for (CartItemEntity cartItemEntity : cartItemEntityList)
        {
            total = total.add(cartItemEntity.getProduct().getPrice().multiply(new BigDecimal( cartItemEntity.getQuantity())));
        }

    }

    public List<TransactionProductEntity> getProductsHasInTransaction() {
        return productsHasInTransaction;
    }

    public void setProductsHasInTransaction(List<TransactionProductEntity> productsHasInTransaction) {
        this.productsHasInTransaction = productsHasInTransaction;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
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
}
