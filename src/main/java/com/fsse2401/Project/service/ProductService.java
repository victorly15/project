package com.fsse2401.Project.service;

import com.fsse2401.Project.data.product.domainObject.ProductResponseData;
import com.fsse2401.Project.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponseData> getAllproducts();

    ProductResponseData getProductById(Integer pid);

    ProductEntity getEntityById(Integer pid);
}
