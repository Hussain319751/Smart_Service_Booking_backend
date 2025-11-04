package com.ssb.service;


import com.ssb.dto.BookingDTO;
import java.util.List;

public interface BookingService {
    BookingDTO create(BookingDTO dto);
    BookingDTO update(Long id, BookingDTO dto);
    BookingDTO getById(Long id);
    List<BookingDTO> getAll();
    List<BookingDTO> getByUser(Long userId);
    void delete(Long id);
}
