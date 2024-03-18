package com.fsse2401.Project.Util;

import com.fsse2401.Project.data.product.domainObject.ProductResponseData;
import com.fsse2401.Project.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2401.Project.data.product.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductDataUtil {
    public static List<ProductResponseData> toResponseDataList(List<ProductEntity> productEntityList){
        List<ProductResponseData> productResponseDataList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList)
        {
            ProductResponseData productResponseData = new ProductResponseData(productEntity);
            productResponseDataList.add(productResponseData);
        }
        return productResponseDataList;
    }

    public static List<GetAllProductResponseDto> toProductResponseDtoList(List<ProductResponseData> productResponseDataList)
    {
        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();

        for(ProductResponseData productResponseData: productResponseDataList)
        {
            GetAllProductResponseDto getAllProductResponseDto = new GetAllProductResponseDto(productResponseData);
            getAllProductResponseDtoList.add(getAllProductResponseDto);
        }

        return getAllProductResponseDtoList;
    }
}
