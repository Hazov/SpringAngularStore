package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.*;
import ru.voronasever.voronaStore.payload.request.ForgotPasswordRequest;
import ru.voronasever.voronaStore.payload.request.LoginRequest;
import ru.voronasever.voronaStore.payload.request.SignupRequest;
import ru.voronasever.voronaStore.payload.response.JwtResponse;
import ru.voronasever.voronaStore.payload.response.MessageResponse;
import ru.voronasever.voronaStore.repositories.ICartRepo;
import ru.voronasever.voronaStore.repositories.IRoleRepo;
import ru.voronasever.voronaStore.secuirty.jwt.JwtUtils;
import ru.voronasever.voronaStore.secuirty.UserDetailsImpl;
import ru.voronasever.voronaStore.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    MailSender mailSender;
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    IRoleRepo roleRepository;

    @Autowired
    ICartRepo cartRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println(jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthority().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByLogin(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
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
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ForgotPasswordRequest forgotRequest) {

        String email = forgotRequest.getEmail();
        System.out.println(email);
        if(userService.existsByLogin(forgotRequest.getEmail())){
            final SimpleMailMessage simpleMail = new SimpleMailMessage();
            simpleMail.setFrom("fs_belayavorona@mail.ru");
            simpleMail.setTo(email);
            simpleMail.setSubject("Восстановление учетной записи на voronasever.ru");
            //String link = remind/?key=                 TODO шифрование в ссылку
            simpleMail.setText("По вашему адресу был сделан запрос на восстановление учетной записи на ВоронаСевер.Ру" +
                    " (www.voronasever.ru).\n" +
                    "\n" + "Для получения новых данных пройдите по этой ссылке: ");

            mailSender.send(simpleMail);
            System.out.println("super");
            return ResponseEntity.ok(new MessageResponse("На ваш адрес: " + email + " было отправлено письмо."));
        }
        return ResponseEntity.ok(new MessageResponse("Пользователя с таким email не существует"));
    }
}
