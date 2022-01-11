package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.*;
import ru.voronasever.voronaStore.payload.request.SignupRequest;
import ru.voronasever.voronaStore.repositories.ICartRepo;
import ru.voronasever.voronaStore.repositories.IRoleRepo;
import ru.voronasever.voronaStore.repositories.IUserRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepository;
    @Autowired
    IRoleRepo roleRepository;
    @Autowired
    ICartRepo cartRepository;
    @Autowired
    PasswordEncoder encoder;


    User getUserByEmail(String email){
        return userRepository.findUserByLogin(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean existsByName(String login) {
        return userRepository.existsByName(login);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByName(username);
    }
    User getCurrentUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByUsername(principal.getUsername());
    }

    public void createNewUserAccount(SignupRequest signUpRequest) {
        User user = new User(0, signUpRequest.getName(),
                signUpRequest.getName(), encoder.encode(signUpRequest.getPass()),
                "", null, null,
                null, null, new ArrayList<Address>());

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
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        save(user);
    }
}
