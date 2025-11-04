package com.ssb.service.impl;


import com.ssb.dto.UserDTO;
import com.ssb.entity.User;
import com.ssb.exception.ResourceNotFoundException;
import com.ssb.repository.UserRepository;
import com.ssb.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    private UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    private User toEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone());
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        return toDto(repo.save(existing));
    }

    @Override
    public UserDTO getById(Long id) {
        return repo.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
