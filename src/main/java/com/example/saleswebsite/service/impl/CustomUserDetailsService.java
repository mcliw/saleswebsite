package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.entity.User;
import com.example.saleswebsite.repository.UserRepository;
import com.example.saleswebsite.repository.RoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// Removed import to avoid ambiguity with entity.User
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found: " + username);
        String roleName = "USER";
        if (user.getRoleId() != null) {
            roleRepository.findById(user.getRoleId()).ifPresent(r -> {
            });
        }
        // try to resolve roleName if possible
        if (user.getRoleId() != null) {
            var r = roleRepository.findById(user.getRoleId());
            if (r.isPresent() && r.get().getRoleName() != null) {
                roleName = r.get().getRoleName();
            }
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword() == null ? "" : user.getPassword(), Collections.singleton(authority));
    }
}
