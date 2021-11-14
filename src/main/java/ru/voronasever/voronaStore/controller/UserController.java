package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.ResourceDecoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.services.UserService;


import java.beans.Encoder;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users/")
public class UserController {
    @Autowired
    UserService userService;


//    @GetMapping("/reset-password")
//    List<Product> resetPassword(@RequestParam String key){
//        Encoder encoder = new Encoder();
//        BCryptPasswordEncoder f = new BCryptPasswordEncoder();
//        f.matches(key);
//        f.
//    }

}
