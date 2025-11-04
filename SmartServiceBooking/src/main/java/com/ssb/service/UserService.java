package com.ssb.service;

import com.ssb.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO create(UserDTO dto);
    UserDTO update(Long id, UserDTO dto);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
    void delete(Long id);
}
