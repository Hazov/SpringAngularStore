package ru.voronasever.voronaStore.services;

import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.Address;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.payload.request.NewAddressStr;
import ru.voronasever.voronaStore.repositories.IAddressRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    IAddressRepo addressRepository;
    @Autowired
    UserService userService;

    public List<String> getAddresses() {
        Collection<Address> addresses = getCurrentUser().getAddresses();
        return addresses.stream().map(Address::getPath).collect(Collectors.toList());
    }


    //TODO REFACTOR (Single Responsibility)
    public List<String> addAddress(String addressPath) {
        User user = getCurrentUser();
        Address address = new Address(0L, addressPath);
        Address savedAddress = addressRepository.save(address);
        Collection<Address> addresses = user.getAddresses();
        addresses.add(savedAddress);
        userService.save(user);
        return addresses.stream().map(Address::getPath).collect(Collectors.toList());
    }

    User getCurrentUser(){
        return userService.getCurrentUser();
    }

    public Address getAddress(String addressPath) {
        Address address = addressRepository.findByPath(addressPath);
        if(address == null){
            address = new Address(0L,addressPath);
            User currentUser = getCurrentUser();
            currentUser.getAddresses().add(address);
            userService.save(currentUser);
        }
            return address;
    }

}
