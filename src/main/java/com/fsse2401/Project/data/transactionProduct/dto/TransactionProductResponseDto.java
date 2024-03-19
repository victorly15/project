package com.fsse2401.Project.data.transactionProduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.Project.data.product.domainObject.ProductResponseData;
import com.fsse2401.Project.data.product.dto.response.ProductResponseDto;
import com.fsse2401.Project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.Project.data.transactionProduct.domainObject.response.TransactionProductResponseData;

import java.math.BigDecimal;

public class TransactionProductResponseDto {

    private Integer tpid;
    @JsonProperty("product")
    private ProductResponseDto productResponseDto;
    private Integer quantity;
    private BigDecimal subtotal = BigDecimal.ZERO;

    public TransactionProductResponseDto(TransactionProductResponseData transactionProductResponseData) {
        this.tpid = transactionProductResponseData.getTpid();
        this.productResponseDto = new ProductResponseDto(transactionProductResponseData.getProductResponseData());
        this.quantity = transactionProductResponseData.getQuantity();
        this.subtotal = transactionProductResponseData.getSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProductResponseDto() {
        return productResponseDto;
    }

    public void setProductResponseDto(ProductResponseDto productResponseDto) {
        this.productResponseDto = productResponseDto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
