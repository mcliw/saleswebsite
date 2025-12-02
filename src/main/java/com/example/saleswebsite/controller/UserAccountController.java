package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.UserAccountDTO;
import com.example.saleswebsite.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAll() {
        return ResponseEntity.ok(userAccountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDTO> getById(@PathVariable Long id) {
        UserAccountDTO dto = userAccountService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserAccountDTO> create(@RequestBody UserAccountDTO dto) {
        return ResponseEntity.ok(userAccountService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccountDTO> update(@PathVariable Long id, @RequestBody UserAccountDTO dto) {
        UserAccountDTO updated = userAccountService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-username")
    public ResponseEntity<UserAccountDTO> findByUsername(@RequestParam String username) {
        UserAccountDTO dto = userAccountService.findByUsername(username);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
