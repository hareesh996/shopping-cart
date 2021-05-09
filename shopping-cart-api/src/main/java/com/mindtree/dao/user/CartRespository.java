package com.mindtree.dao.user;

import com.mindtree.entities.user.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRespository extends JpaRepository<Cart, Long> {

    /**
     * Retrieve the Cart of the user.
     *
     * @param userId
     * @return
     */
    @EntityGraph(value = "cart.cartProducts")
    Cart findByUserUserId(Long userId);

    Cart save(Cart cart);

}
