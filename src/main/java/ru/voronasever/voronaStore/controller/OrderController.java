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
        try {
            String addressStr = orderRequest.getAddress();
            Cart cart = cartService.getCart();
            String phoneNumber = orderRequest.getPhoneNumber();
            if(cart.getProducts().size() <= 0 || addressStr == null || phoneNumber == null) return false;
            User currentUser = getCurrentUser();
            Address address = addressService.getAddress(addressStr);
            int totalPrice = cartService.getTotalPrice(cart);
            Order order = new Order(0L, currentUser, phoneNumber, totalPrice, cart.getProducts(), address);
            cartService.resetCart(cart);
            orderService.createNewOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    User getCurrentUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByUsername(principal.getUsername());
    }
}
