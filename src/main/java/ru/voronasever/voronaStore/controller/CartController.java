package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.payload.response.SortedCartResponse;
import ru.voronasever.voronaStore.payload.response.SortedProduct;
import ru.voronasever.voronaStore.services.CartService;
import ru.voronasever.voronaStore.services.UserService;

import java.util.List;


@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;


    @GetMapping()
    @ResponseBody
    ResponseEntity<?> getSortedCart(){
        //SortedCartResponse sortedCart = cartService.getSortedCart();
        SortedCartResponse sortedCart = cartService.getSortedCart2();

        return ResponseEntity.ok().body(sortedCart);
    }

    @PutMapping(value = "/add")
    ResponseEntity<?>  addToCart(@RequestBody Product product) {
        cartService.addProductToCart(cartService.getCart(), product);
        return getSortedCart();

    }

    @PutMapping ("/delete")
    ResponseEntity<?>  removeFromCart(@RequestBody Product product) {
        Cart cartById = cartService.getCart();
        cartService.removeProductFromCart(cartById, product);
        return getSortedCart();
    }
    @PutMapping ("/inc")
    ResponseEntity<?>  incrementProductFromCart(@RequestBody Product product) {
        cartService.addProductToCart(cartService.getCart(), product);
        return getSortedCart();
    }
    @PutMapping ("/dec")
    ResponseEntity<?>  decrementProductFromCart(@RequestBody Product product) {
        cartService.removeOneProductFromCart(cartService.getCart(), product);
        return getSortedCart();
    }





}

