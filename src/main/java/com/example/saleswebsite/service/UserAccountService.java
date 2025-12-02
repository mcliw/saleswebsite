package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.UserAccountDTO;
import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> findAll();

    UserAccountDTO findById(Long id);

    UserAccountDTO create(UserAccountDTO dto);

    UserAccountDTO update(Long id, UserAccountDTO dto);

    void delete(Long id);

    UserAccountDTO findByUsername(String username);
}
