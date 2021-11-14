package ru.voronasever.voronaStore.services;

import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.Address;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.repositories.IAddressRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    IAddressRepo addressRepository;
    @Autowired
    UserService userService;

    public List<String> getAddresses() {
        return getCurrentUser().getAddresses().stream().map(Address::getPath).collect(Collectors.toList());
    }

    public void addAddress(String addressPath) {
        User currentUser = getCurrentUser();
        Address address = new Address((short) 0,addressPath,currentUser);
        currentUser.getAddresses().add(address);
        addressRepository.save(address);

    }

    User getCurrentUser(){
        return userService.getCurrentUser();
    }
}
