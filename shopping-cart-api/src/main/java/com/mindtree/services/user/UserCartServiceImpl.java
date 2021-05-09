package com.mindtree.services.user;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.mindtree.core.exception.BusinessException;
import com.mindtree.dao.product.ProductRepository;
import com.mindtree.dao.user.CartRespository;
import com.mindtree.entities.product.Product;
import com.mindtree.entities.user.Cart;
import com.mindtree.entities.user.CartProduct;
import com.mindtree.web.dto.product.ProductDto;
import com.mindtree.web.dto.user.CartDto;
import com.mindtree.web.dto.user.CartRequest;
import com.mindtree.web.dto.user.UserDto;
import com.mindtree.web.mappers.CartProductDtoMapper;
import com.mindtree.web.mappers.ProductDtoMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserCartServiceImpl implements UserCartService {

    private final CartRespository cartRespository;
    private final ProductDtoMapper productDtoMapper;
    private final ProductRepository productRepository;
    private final CartProductDtoMapper cartProductDtoMapper;

    UserCartServiceImpl(CartRespository cartRespository, ProductDtoMapper productDtoMapper, ProductRepository productRepository,
                        CartProductDtoMapper cartProductDtoMapper) {
        this.cartRespository = cartRespository;
        this.productDtoMapper = productDtoMapper;
        this.productRepository = productRepository;
        this.cartProductDtoMapper = cartProductDtoMapper;
    }

    @Override
    public CartDto getCartOfUser(UserDto userDto) {
        Cart cart = this.getCart(userDto);
        if (Objects.isNull(cart)) {
            throw new BusinessException("user.invalid", "Invalid User Details");
        }
        return this.cartProductDtoMapper.convertToCartDto(cart);
    }

    private Cart getCart(UserDto userDto) {
        return this.cartRespository.findByUserUserId(userDto.getUserId());
    }

    @Transactional()
    @Override
    public CartDto addProduct(CartRequest cartRequest) {
        Cart cart = this.getCart(cartRequest.getUser());
        Long productId = cartRequest.getProductDto().getProductId();
        CartProduct cartProductToUpdate = Iterables.tryFind(cart.getCartProducts(), input -> input.getProduct().getProductId() == productId)
                .or(() -> {
                    CartProduct cartProduct = new CartProduct();
                    cartProduct.setCart(cart);
                    Product product = this.productRepository.getOne(productId);
                    cartProduct.setProduct(product);
                    return cartProduct;
                });
        int noOfQuantities = cartRequest.getNumberOfQuantities() <= 0 ? 1 : cartRequest.getNumberOfQuantities();
        cartProductToUpdate.setNumberOfQuantities(noOfQuantities);
        if (Objects.isNull(cart.getCartProducts())) {
            cart.setCartProducts(new ArrayList<>());
        }
        cart.getCartProducts().add(cartProductToUpdate);
        this.cartRespository.save(cart);
        return this.cartProductDtoMapper.convertToCartDto(cart);
    }

    @Transactional()
    @Override
    public java.util.Optional<ProductDto> removeProduct(CartRequest cartRequest) {
        Cart cart = this.getCart(cartRequest.getUser());
        Optional<CartProduct> cartProduct = Iterables.tryFind(cart.getCartProducts(),
                (cartProducItem) -> cartProducItem.getProduct().getProductId() == cartRequest.getProductDto().getProductId());
        if (!cartProduct.isPresent()) {
            return java.util.Optional.empty();
        }
        int remainingNumberOfQuantities = cartProduct.get().getNumberOfQuantities() - cartRequest.getNumberOfQuantities();
        cartProduct.get().setNumberOfQuantities(remainingNumberOfQuantities);
        if (remainingNumberOfQuantities <= 0) {
            cart.getCartProducts().remove(cartProduct.get());
        }
        this.cartRespository.save(cart);
        return java.util.Optional.of(this.productDtoMapper.toProductDto(cartProduct.get().getProduct()));
    }
}
