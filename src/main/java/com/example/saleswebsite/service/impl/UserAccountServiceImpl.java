package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.UserDTO;
import com.example.saleswebsite.entity.User;
import com.example.saleswebsite.repository.UserRepository;
import com.example.saleswebsite.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private static final Logger log = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    private final UserRepository userAccountRepository;

    public UserAccountServiceImpl(UserRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        return userAccountRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userAccountRepository.findById(id).map(UserDTO::fromEntity).orElse(null);
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO dto) {
        log.info("Creating user {}", dto.getUsername());
        User e = dto.toEntity();
        User saved = userAccountRepository.save(e);
        return UserDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        return userAccountRepository.findById(id).map(existing -> {
            log.info("Updating user {}", id);
            User updated = dto.toEntity();
            updated.setId(id);
            User saved = userAccountRepository.save(updated);
            return UserDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("UserAccount not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting user {}", id);
        userAccountRepository.deleteById(id);
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userAccountRepository.findByUsername(username) == null ? null
                : UserDTO.fromEntity(userAccountRepository.findByUsername(username));
    }
}
