package com.fsse2401.Project.api;

import com.fsse2401.Project.Util.JwtUtil;
import com.fsse2401.Project.data.CartItem.Dto.response.CartItemResponseDto;
import com.fsse2401.Project.data.CartItem.Dto.response.ResultStatusResponseDto;
import com.fsse2401.Project.data.CartItem.dominaObject.CartItemResponseData;
import com.fsse2401.Project.data.CartItem.dominaObject.Result;
import com.fsse2401.Project.service.CartItemService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PutMapping("/{pid}/{quantity}")
    public ResultStatusResponseDto putCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){

        return new ResultStatusResponseDto(cartItemService.putCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity));
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCartItems(JwtAuthenticationToken jwtToken)
    {
       List<CartItemResponseData> cartItemResponseDataList = cartItemService.getUserCartItems(JwtUtil.getFirebaseUserData(jwtToken));

       List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
       for (CartItemResponseData cartItemResponseData : cartItemResponseDataList)
       {
           CartItemResponseDto cartItemResponseDto = new CartItemResponseDto(cartItemResponseData);
           cartItemResponseDtoList.add(cartItemResponseDto);
       }

       return cartItemResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto updateCartQuantity(@PathVariable Integer pid, @PathVariable Integer quantity, JwtAuthenticationToken jwtToken){

        return new CartItemResponseDto(cartItemService.upDateCartItem(pid, quantity, JwtUtil.getFirebaseUserData(jwtToken)));

    }

    @DeleteMapping("/{pid}")
    public ResultStatusResponseDto deleteCartItemByPid(@PathVariable Integer pid, JwtAuthenticationToken jwtToken){

        return new ResultStatusResponseDto(cartItemService.deleteCartItemByPid(JwtUtil.getFirebaseUserData(jwtToken), pid));
    }
}