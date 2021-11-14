package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voronasever.voronaStore.services.AddressService;
import ru.voronasever.voronaStore.services.UserService;

import java.util.List;

@RestController

@RequestMapping("api/v1/addresses")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/user-addresses")
    List<String> getAddresses(){
        return addressService.getAddresses();
    }
    @PutMapping("/add")
    void addAddress(String address){
        addressService.addAddress(address);
    }

}
