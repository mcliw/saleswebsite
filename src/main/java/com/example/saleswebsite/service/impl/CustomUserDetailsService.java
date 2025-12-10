package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.entity.UserAccount;
import com.example.saleswebsite.repository.UserAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository repository;

    public CustomUserDetailsService(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = repository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found: " + username);
        GrantedAuthority authority = new SimpleGrantedAuthority(
                "ROLE_" + (user.getRole() == null ? "USER" : user.getRole()));
        return new User(user.getUsername(), user.getPassword() == null ? "" : user.getPassword(),
                Collections.singleton(authority));
    }
}
