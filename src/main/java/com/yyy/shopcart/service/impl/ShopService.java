package com.yyy.shopcart.service.impl;

import com.yyy.shopcart.model.Cart;
import com.yyy.shopcart.model.CartItem;
import com.yyy.shopcart.model.Product;
import com.yyy.shopcart.repository.CartItemRepository;
import com.yyy.shopcart.repository.CartRepository;
import com.yyy.shopcart.repository.ProductRepository;
import com.yyy.shopcart.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ShopService implements IShopService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<CartItem> findAllCartItem() {
        return (List<CartItem>) cartItemRepository.findAll();
    }

    @Override
    public List<Cart> findAllCart() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public CartItem findCartItemById(long id) {
        Optional<CartItem> optionalCartItem =  cartItemRepository.findById(id);
        if(optionalCartItem.isPresent()){
            return optionalCartItem.get();
        }
        throw new RuntimeException("khogn tim thay Cart item nay");
    }

    @Override
    public void removeCartItemById(long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Product findProductById(long product_id) {
        Optional<Product> optionalProduct =  productRepository.findById(product_id);
        if(optionalProduct.isPresent())
            return optionalProduct.get();
        throw new RuntimeException("Khong tim thay product ");
    }

    @Override
    public CartItem findCartItemByCartAndProduct(Cart cart, Product product) {
        return cartItemRepository.findCartItemByCartAndProduct(cart,product);
    }

    @Override
    public List<CartItem> findCartItemByCart(Cart cart) {
        return (List<CartItem>) cartItemRepository.findCartItemsByCart(cart);
    }

    @Override
    public List<CartItem> findCartItemsByCart(Cart cart) {
        return cartItemRepository.findCartItemsByCart(cart);
    }
}
