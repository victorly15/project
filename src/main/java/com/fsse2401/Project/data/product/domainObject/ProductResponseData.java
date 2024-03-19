package com.fsse2401.Project.data.product.domainObject;

import com.fsse2401.Project.data.product.entity.ProductEntity;
import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;

import java.math.BigDecimal;

public class ProductResponseData {

    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductResponseData(ProductEntity productEntity) {
        this.pid = productEntity.getPid();
        this.name = productEntity.getName();
        this.description = productEntity.getDescription();
        this.imageUrl = productEntity.getImageUrl();
        this.price = productEntity.getPrice();
        this.stock = productEntity.getStock();
    }

    public ProductResponseData(TransactionProductEntity transactionProductEntity) {
        this.pid = transactionProductEntity.getPid();
        this.name = transactionProductEntity.getName();
        this.description = transactionProductEntity.getDescription();
        this.imageUrl = transactionProductEntity.getImageUrl();
        this.price = transactionProductEntity.getPrice();
        this.stock = transactionProductEntity.getStock();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
