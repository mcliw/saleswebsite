package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.UserAccountDTO;
import com.example.saleswebsite.entity.UserAccount;
import com.example.saleswebsite.repository.UserAccountRepository;
import com.example.saleswebsite.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserAccountDTO> findAll() {
        return userAccountRepository.findAll().stream().map(UserAccountDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UserAccountDTO findById(Long id) {
        return userAccountRepository.findById(id).map(UserAccountDTO::fromEntity).orElse(null);
    }

    @Override
    public UserAccountDTO create(UserAccountDTO dto) {
        UserAccount e = dto.toEntity();
        UserAccount saved = userAccountRepository.save(e);
        return UserAccountDTO.fromEntity(saved);
    }

    @Override
    public UserAccountDTO update(Long id, UserAccountDTO dto) {
        return userAccountRepository.findById(id).map(existing -> {
            UserAccount updated = dto.toEntity();
            updated.setId(id);
            UserAccount saved = userAccountRepository.save(updated);
            return UserAccountDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userAccountRepository.deleteById(id);
    }

    @Override
    public UserAccountDTO findByUsername(String username) {
        return userAccountRepository.findByUsername(username) == null ? null
                : UserAccountDTO.fromEntity(userAccountRepository.findByUsername(username));
    }
}
