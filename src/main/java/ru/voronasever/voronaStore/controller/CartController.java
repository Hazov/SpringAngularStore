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
        return cartService.getUserCart();
    }

    @PutMapping(value = "/add")
    void addToCart(@RequestBody Product product) {
        cartService.addProductToCart(cartService.getUserCart(), product);
    }


    @PutMapping ("/delete")
    Cart removeFromCart(@RequestBody Product product) {
        Cart cartById = cartService.getUserCart();
        cartService.removeProductFromCart(cartById, product);
        return cartById;
    }
    @PutMapping ("/inc")
    void incrementProductFromCart(@RequestBody Product product) {
        cartService.addProductToCart(cartService.getUserCart(), product);
    }
    @PutMapping ("/dec")
    void decrementProductFromCart(@RequestBody Product product) {
        cartService.removeOneProductFromCart(cartService.getUserCart(), product);
    }





}

