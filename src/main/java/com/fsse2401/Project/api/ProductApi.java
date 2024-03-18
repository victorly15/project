package com.fsse2401.Project.api;

import com.fsse2401.Project.Util.ProductDataUtil;
import com.fsse2401.Project.data.product.domainObject.ProductResponseData;
import com.fsse2401.Project.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2401.Project.data.product.dto.response.ProductResponseDto;
import com.fsse2401.Project.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductApi {

    final private ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GetAllProductResponseDto> getAllProducts(){
        List<ProductResponseData> productResponseDataList = productService.getAllproducts();
        return ProductDataUtil.toProductResponseDtoList(productResponseDataList);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Integer id)
    {
        return new ProductResponseDto(productService.getProductById(id));
    }
}
