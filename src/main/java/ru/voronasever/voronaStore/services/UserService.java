package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.*;
import ru.voronasever.voronaStore.payload.request.SignupRequest;
import ru.voronasever.voronaStore.repositories.jpa.CartRepository;
import ru.voronasever.voronaStore.repositories.jpa.RoleRepository;
import ru.voronasever.voronaStore.repositories.jpa.UserRepository;
import ru.voronasever.voronaStore.secuirty.UserDetailsImpl;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private static final int AUTO_INCREMENT = 0;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    PasswordEncoder encoder;


    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean existsByEmail(String login) {
        return userRepository.existsByEmail(login);
    }


    User getCurrentUser(){
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByEmail(principal.getEmail()).orElseThrow();
    }

    public void createNewUserAccount(SignupRequest signUpRequest) {
        User user = new User(AUTO_INCREMENT, signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPass()), signUpRequest.getName(),
                "", null, new ArrayList<Product>(),
                new ArrayList<Review>(), null, new ArrayList<Address>(), new ArrayList<Order>());

        String strRole = signUpRequest.getRole();
        Role role = null;

        if (strRole == null) {
            role = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            switch (strRole) {
                case "admin":
                    role = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    break;
                default:
                    role = roleRepository.findByName(RoleEnum.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
        }
        Cart cart = new Cart();
        cartRepository.save(cart);

        user.setCart(cart);
        user.setRole(role);
        save(user);
    }
}
