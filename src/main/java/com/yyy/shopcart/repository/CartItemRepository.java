package com.yyy.shopcart.repository;

import com.yyy.shopcart.model.Cart;
import com.yyy.shopcart.model.CartItem;
import com.yyy.shopcart.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {
    List<CartItem> findCartItemsByCart(Cart cart);
    CartItem findCartItemByCartAndProduct(Cart cart, Product product);
}
