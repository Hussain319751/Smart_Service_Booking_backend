package com.ssb.service.impl;


import com.ssb.dto.BookingDTO;
import com.ssb.entity.Booking;
import com.ssb.entity.ServiceEntity;
import com.ssb.entity.User;
import com.ssb.exception.ResourceNotFoundException;
import com.ssb.repository.BookingRepository;
import com.ssb.repository.ServiceRepository;
import com.ssb.repository.UserRepository;
import com.ssb.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final UserRepository userRepo;
    private final ServiceRepository serviceRepo;

    public BookingServiceImpl(BookingRepository repo,
                              UserRepository userRepo,
                              ServiceRepository serviceRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.serviceRepo = serviceRepo;
    }

    private BookingDTO toDto(Booking b) {
        return new BookingDTO(
            b.getId(),
            b.getUser().getId(),
            b.getService().getId(),
            b.getBookingDate(),
            b.getStatus(),
            b.getLocation()
        );
    }

    @Override
    public BookingDTO create(BookingDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        ServiceEntity service = serviceRepo.findById(dto.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setBookingDate(dto.getBookingDate());
        booking.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        booking.setLocation(dto.getLocation());
        return toDto(repo.save(booking));
    }

    @Override
    public BookingDTO update(Long id, BookingDTO dto) {
        Booking existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (dto.getBookingDate() != null)
            existing.setBookingDate(dto.getBookingDate());

        if (dto.getStatus() != null)
            existing.setStatus(dto.getStatus());

        return toDto(repo.save(existing));
    }

    @Override
    public BookingDTO getById(Long id) {
        return repo.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    @Override
    public List<BookingDTO> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getByUser(Long userId) {
        return repo.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
