package com.ticketing.ticketing_backend.Config;
import com.ticketing.ticketing_backend.JWT.UserInfoUserDetailsService;
import com.ticketing.ticketing_backend.Model.User;
import com.ticketing.ticketing_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsService extends UserInfoUserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user by email using the repository
        Optional<User> userOptional = repository.findByUserEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Map the single `userRole` to authorities
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getUserRole());

            return new org.springframework.security.core.userdetails.User(
                    user.getUserEmail(), // Username (email in this case)
                    user.getPassword(),  // Encoded password
                    List.of(authority)   // Authorities list with a single role
            );
        } else {
            throw new UsernameNotFoundException("User with email " + email + " not found!");
        }
    }
}
