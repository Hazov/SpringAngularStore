package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.Role;
import ru.voronasever.voronaStore.model.RoleEnum;
import ru.voronasever.voronaStore.repositories.jpa.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    public Role checkRole(String roleStr) {
        Role role;
        if (roleStr == null) {
            role = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            switch (roleStr) {
                case "admin":
                    role = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    break;
                default:
                    role = roleRepository.findByName(RoleEnum.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
        }
        return role;
    }
}
