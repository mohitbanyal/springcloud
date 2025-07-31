package com.mb.springcloud.couponservice.security;

import com.mb.springcloud.couponservice.model.User;
import com.mb.springcloud.couponservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSecurityImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
        Optional<User> userOpt = userRepository. findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found for email" + username);
        }
        User user = userOpt.get();
        System.out.println("User " + user + " username " + username);

            System.out.println("Roles " + user.getRoles());
            user.getRoles().forEach(role -> System.out.println("Role name: " + role.getName()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    }

