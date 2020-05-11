package com.yyy.shopcart.controller;


import com.yyy.shopcart.model.Cart;
import com.yyy.shopcart.model.CartItem;
import com.yyy.shopcart.model.Product;
import com.yyy.shopcart.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class ShopController {
    @Autowired
    IShopService shopService;

    @ModelAttribute("cart")
    public Cart shopCart(){
        Cart cart =  new Cart();
        shopService.saveCart(cart);
        return cart;
    }

    @GetMapping("/index")
    public ModelAndView showHomepage(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> products = shopService.findAllProduct();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/add-to-cart/{product_id}")
    public String addToCart(@PathVariable long product_id,@ModelAttribute("cart") Cart cart){
        Product product = shopService.findProductById(product_id);
        CartItem cartItem = shopService.findCartItemByCartAndProduct(cart,product);
        if(cartItem != null){
            cartItem.setQuantity(1 + cartItem.getQuantity());
            cartItem.setTotalPrice(cartItem.getTotalPrice() + product.getPrice());
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
            cartItem.setCart(cart);
        }
        shopService.saveCartItem(cartItem);
        return "redirect:/index";
    }

    @GetMapping("/shop-cart")
    public ModelAndView showCart(@ModelAttribute("cart") Cart cart){
        List<CartItem> cartItems = shopService.findCartItemsByCart(cart);
        long total = 0 ;
        for(CartItem cartItem : cartItems){
            total += cartItem.getTotalPrice();
        }
        cart.setTotalPrice(total);
        shopService.saveCart(cart);
        ModelAndView modelAndView = new ModelAndView("shop-cart");
        modelAndView.addObject("cartItems",cartItems);
        return modelAndView;
    }

    @PostMapping("/edit-cart")
    public String editCart(@RequestParam("quantity") long quantity,@RequestParam("id") long cartItemId){
        CartItem cartItem = shopService.findCartItemById(cartItemId);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(quantity * cartItem.getProduct().getPrice());
        shopService.saveCartItem(cartItem);
        return "redirect:/shop-cart";
    }

    @PostMapping("/remove-cart-item/{cartItem_id}")
    public String removeCartItem(@PathVariable long cartItem_id){
        shopService.removeCartItemById(cartItem_id);
        return "redirect:/shop-cart";
    }
}
