package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.payload.request.ForgotPasswordRequest;
import ru.voronasever.voronaStore.payload.request.LoginRequest;
import ru.voronasever.voronaStore.payload.request.SignupRequest;
import ru.voronasever.voronaStore.payload.response.JwtResponse;
import ru.voronasever.voronaStore.payload.response.MessageResponse;
import ru.voronasever.voronaStore.repositories.jpa.CartRepository;
import ru.voronasever.voronaStore.repositories.jpa.RoleRepository;
import ru.voronasever.voronaStore.secuirty.jwt.JwtUtils;
import ru.voronasever.voronaStore.secuirty.UserDetailsImpl;
import ru.voronasever.voronaStore.services.MailService;
import ru.voronasever.voronaStore.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    MailService mailService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    JwtUtils jwtUtils;




    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthority().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        userService.createNewUserAccount(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @PostMapping("/forgotPassword")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ForgotPasswordRequest forgotRequest) {

        String email = forgotRequest.getEmail();
        if(userService.existsByEmail(email)){
            mailService.sendRemindPasswordMessageToEmail(email);
            return ResponseEntity.ok(new MessageResponse("На ваш адрес: " + email + " было отправлено письмо."));
        }
        return ResponseEntity.ok(new MessageResponse("Пользователя с таким email не существует"));
    }




}
