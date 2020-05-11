package ua.lviv.lgs.periodicals.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.entities.User;
import ua.lviv.lgs.periodicals.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userMaybe = userRepository.findByUsername(username);
        return userMaybe
                .map(CustomUserDetails::new)
                .orElseThrow(() -> {
                    LOG.error("No user present with username");
                    return new UsernameNotFoundException("No user present with username: " + username);});
    }
}
