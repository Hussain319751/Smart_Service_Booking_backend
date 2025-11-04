package com.ssb.controller;

import com.ssb.dto.UserDTO;
import com.ssb.service.UserService;
import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")	
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

//    @PostMapping
//    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO body) {
//        return ResponseEntity.ok(service.create(body));
//    }
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO body) {
        try {
            return ResponseEntity.ok(service.create(body));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Email already exists");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

    
    @GetMapping
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

