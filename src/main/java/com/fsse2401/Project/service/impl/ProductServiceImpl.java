package com.fsse2401.Project.service.impl;

import com.fsse2401.Project.Util.ProductDataUtil;
import com.fsse2401.Project.data.product.domainObject.ProductResponseData;
import com.fsse2401.Project.data.product.entity.ProductEntity;
import com.fsse2401.Project.data.transaction.Entity.TransactionEntity;
import com.fsse2401.Project.data.transactionProduct.Entity.TransactionProductEntity;
import com.fsse2401.Project.exception.productException.ProductNotFoundException;
import com.fsse2401.Project.repository.ProductRepository;
import com.fsse2401.Project.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductResponseData> getAllproducts(){

        return ProductDataUtil.toResponseDataList(getAllProductEntities());

    }

    @Override
    public ProductResponseData getProductById(Integer pid)
    {

        ProductEntity product = getEntityById(pid);
        ProductResponseData responseData = new ProductResponseData(product);
        return responseData;
    }

    public List<ProductEntity> getAllProductEntities()
    {
        List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();
        return  productEntityList;
    }

    @Override
    public ProductEntity getEntityById(Integer pid)
    {
        return productRepository.findById(pid).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void reduceStock(TransactionEntity transactionEntity){
        for (TransactionProductEntity transactionProductEntity : transactionEntity.getProductsHasInTransaction())
        {
            ProductEntity productEntity = getEntityById(transactionProductEntity.getPid());
            productEntity.setStock(productEntity.getStock() - transactionProductEntity.getQuantity());
            productRepository.save(productEntity);
        }
    }
}
