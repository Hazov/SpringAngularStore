package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voronasever.voronaStore.services.UserService;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
    @Autowired
    UserService userService;

    String getAddresses(){

    }

}
