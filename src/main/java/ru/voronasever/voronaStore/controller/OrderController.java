package ru.voronasever.voronaStore.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.services.UserService;

@RestController
@RequestMapping("http://localhost:8080/api/v1/order")
public class OrderController {
    UserService userService;
    @GetMapping("/addresses")
    String getAddresses(){
        User currentUser = getCurrentUser();

    }
    User getCurrentUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByUsername(principal.getUsername());
    }
}
