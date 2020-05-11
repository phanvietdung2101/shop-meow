package com.yyy.shopcart.service;

import com.yyy.shopcart.model.Cart;
import com.yyy.shopcart.model.CartItem;
import com.yyy.shopcart.model.Product;

import java.util.List;

public interface IShopService {
    List<Product> findAllProduct();
    List<CartItem> findAllCartItem();
    List<Cart> findAllCart();
    CartItem findCartItemById(long id);

    void removeCartItemById(long id);
    void saveCartItem(CartItem cartItem);
    void saveCart(Cart cart);

    Product findProductById(long product_id);
    CartItem findCartItemByCartAndProduct(Cart cart, Product product);
    List<CartItem> findCartItemByCart(Cart cart);

    List<CartItem> findCartItemsByCart(Cart cart);
}
