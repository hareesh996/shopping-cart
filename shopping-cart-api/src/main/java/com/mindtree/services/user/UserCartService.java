package com.mindtree.services.user;

import com.mindtree.web.dto.product.ProductDto;
import com.mindtree.web.dto.user.CartDto;
import com.mindtree.web.dto.user.CartRequest;
import com.mindtree.web.dto.user.UserDto;

import java.util.Optional;

public interface UserCartService {

    public CartDto getCartOfUser(UserDto userDto);

    public CartDto addProduct(CartRequest cartRequest);

    public Optional<ProductDto> removeProduct(CartRequest cartRequest);

}
