package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.UserDTO;
import java.util.List;

public interface UserAccountService {
    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO create(UserDTO dto);

    UserDTO update(Long id, UserDTO dto);

    void delete(Long id);

    UserDTO findByUsername(String username);
}
