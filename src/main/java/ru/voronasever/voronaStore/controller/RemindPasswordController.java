package ru.voronasever.voronaStore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voronasever.voronaStore.model.RemindPass;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.payload.request.ForgotChangePasswordRequest;
import ru.voronasever.voronaStore.payload.response.MessageResponse;
import ru.voronasever.voronaStore.services.RemindPassService;
import ru.voronasever.voronaStore.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/remind")
public class RemindPasswordController {
    @Autowired
    RemindPassService remindPassService;
    @Autowired
    UserService userService;

    @PostMapping
    ResponseEntity<MessageResponse> changePassword(@RequestBody ForgotChangePasswordRequest request){
        String eenc = request.getEenc();
        String hash = request.getKey();
        String pass = request.getPass();
        System.out.println(eenc);
        System.out.println(hash);
        System.out.println(pass);

        if(remindPassService.verifyLinkData(hash,eenc)){
            Optional<RemindPass> byHash = remindPassService.findByHash(hash);
            if(byHash.isPresent()){
                String email = byHash.get().getEmail();
                User currentUser = userService.getUserByEmail(email).orElseThrow();
                currentUser.setPass(pass);
                currentUser.setName("EEE");
                userService.save(currentUser);
                return ResponseEntity.ok(new MessageResponse("Пароль успешно изменен!"));
            }
        };
        return ResponseEntity.ok(new MessageResponse("Пароль не изменен. Что-то пошло не так..."));
    }
}
