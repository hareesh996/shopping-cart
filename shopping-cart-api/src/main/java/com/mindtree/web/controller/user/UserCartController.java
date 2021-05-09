package com.mindtree.web.controller.user;

import com.mindtree.entities.user.Cart;
import com.mindtree.services.user.UserCartService;
import com.mindtree.web.dto.Response;
import com.mindtree.web.dto.product.ProductDto;
import com.mindtree.web.dto.user.CartDto;
import com.mindtree.web.dto.user.CartRequest;
import com.mindtree.web.dto.user.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("cart")
public class UserCartController {

    private UserCartService userCartService;

    UserCartController(UserCartService userCartService) {
        this.userCartService = userCartService;
    }

    @PostMapping()
    public ResponseEntity<Response<CartDto>> getUserCart(@RequestBody UserDto userDto) {
        CartDto cartDto = this.userCartService.getCartOfUser(userDto);
        return Response.<CartDto>builder().ok(cartDto);
    }

    @PostMapping(path = "/add-product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<CartDto>> addProductToCart(@RequestBody CartRequest cartRequest) {
        CartDto cartDto = this.userCartService.addProduct(cartRequest);
        return Response.<CartDto>builder().ok(cartDto);
    }

    @DeleteMapping(path = "/remove-product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ProductDto>> removeProductFromCart(@RequestBody CartRequest cartRequest) {
        Optional<ProductDto> productDto = this.userCartService.removeProduct(cartRequest);
        return productDto.isEmpty() ?
                Response.<ProductDto>builder().withError("removeCart.productNtFound") : Response.<ProductDto>builder().ok(productDto.get());
    }

}
