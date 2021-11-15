package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.payload.request.NewAddressStr;
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
    List<String> addAddress(@RequestBody NewAddressStr address){
        return addressService.addAddress(address.getPath());
    }

}
