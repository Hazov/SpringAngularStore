package ru.voronasever.voronaStore.secuirty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.repositories.IUserRepo;
import ru.voronasever.voronaStore.secuirty.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepo userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
