package com.yyy.shopcart.repository;

import com.yyy.shopcart.model.Cart;
import com.yyy.shopcart.model.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart,Long> {
    List<Cart> findAllById(Long id);
}
