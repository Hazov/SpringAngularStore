package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Address;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Order;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.payload.request.CreateOrderRequest;
import ru.voronasever.voronaStore.services.AddressService;
import ru.voronasever.voronaStore.services.CartService;
import ru.voronasever.voronaStore.services.OrderService;
import ru.voronasever.voronaStore.services.UserService;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    AddressService addressService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @PostMapping(value = "/create")
    boolean createOrder(@RequestBody CreateOrderRequest orderRequest){
        final long AUTO_INCREMENT = 0;
        try {
            String addressStr = orderRequest.getAddress();
            String phoneNumber = orderRequest.getPhoneNumber();
            Cart cart = cartService.getCart();
            if(cart.getProducts().size() <= 0 || addressStr == null || phoneNumber == null) return false;
            Address address = addressService.getAddress(addressStr);
            int totalPrice = cartService.getTotalPrice(cart);
            Order order = new Order(AUTO_INCREMENT, phoneNumber, totalPrice, cart.getProducts(), address, cartService.getTotalPrice(cart));
            cartService.resetCart(cart);
            orderService.createNewOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }
    User getCurrentUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByEmail(principal.getUsername()).orElseThrow();
    }
}
