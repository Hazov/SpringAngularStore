package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.repositories.IUserRepo;


import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepository;

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
    public User findUserByLogin(String email){
        return userRepository.findUserByLogin(email);
    }


    public User getUserByUsername(String username) {
        return userRepository.findUserByName(username);
    }
    User getCurrentUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByUsername(principal.getUsername());
    }
}
