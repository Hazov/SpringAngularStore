package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.services.CartService;
import ru.voronasever.voronaStore.services.UserService;


@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Cart getCart(){
        return getUserCart();
    }

    @PutMapping(value = "/add")
    void addToCart(@RequestBody Product product) {
        cartService.addProductToCart(getUserCart(), product);
    }


    @PutMapping ("/delete")
    Cart removeFromCart(@RequestBody Product product) {
        Cart cartById = getUserCart();
        cartService.removeProductFromCart(cartById, product);
        return cartById;
    }
    @PutMapping ("/inc")
    void incrementProductFromCart(@RequestBody Product product) {
        cartService.addProductToCart(getUserCart(), product);
    }
    @PutMapping ("/dec")
    void decrementProductFromCart(@RequestBody Product product) {
        cartService.removeOneProductFromCart(getUserCart(), product);
    }

    Cart getUserCart(){
        return getCartByUserId(getCurrentUser());
    }

    User getCurrentUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByUsername(principal.getUsername());
    }
    Cart getCartByUserId(User user){
        return cartService.getCartById(user.getCart().getId());
    }
}

