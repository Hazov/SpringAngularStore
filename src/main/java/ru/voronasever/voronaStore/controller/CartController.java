package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;
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
    Cart getSortedCart(){
        return cartService.SortedCartWithAmountResponse();
    }

    @PutMapping(value = "/add")
    Cart addToCart(@RequestBody Product product) {
        cartService.addProductToCart(cartService.SortedCartWithAmountResponse(), product);
        return getSortedCart();
    }

    @PutMapping ("/delete")
    Cart removeFromCart(@RequestBody Product product) {
        Cart cartById = cartService.SortedCartWithAmountResponse();
        cartService.removeProductFromCart(cartById, product);
        return cartById;
    }
    @PutMapping ("/inc")
    Cart incrementProductFromCart(@RequestBody Product product) {
        cartService.addProductToCart(cartService.SortedCartWithAmountResponse(), product);
        return getSortedCart();
    }
    @PutMapping ("/dec")
    Cart decrementProductFromCart(@RequestBody Product product) {
        cartService.removeOneProductFromCart(cartService.SortedCartWithAmountResponse(), product);
        return getSortedCart();
    }





}

