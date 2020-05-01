package ua.lviv.lgs.periodicals.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.entities.User;
import ua.lviv.lgs.periodicals.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userMaybe = userRepository.findByUsername(username);
        return userMaybe
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));
    }
}
