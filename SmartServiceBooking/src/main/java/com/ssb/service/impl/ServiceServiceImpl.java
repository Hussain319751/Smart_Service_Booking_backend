package com.ssb.service.impl;

import com.ssb.entity.ServiceEntity;
import com.ssb.exception.ResourceNotFoundException;
import com.ssb.repository.ServiceRepository;
import com.ssb.service.ServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository repo;

    public ServiceServiceImpl(ServiceRepository repo) { 
        this.repo = repo; 
    }

    @Override
    public ServiceEntity create(ServiceEntity service) {
        return repo.save(service);
    }

    @Override
    public ServiceEntity update(Long id, ServiceEntity service) {
        ServiceEntity existing = repo.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        existing.setServiceName(service.getServiceName());
        existing.setDescription(service.getDescription());
        existing.setPrice(service.getPrice());
        return repo.save(existing);
    }

    @Override
    public ServiceEntity getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
    }

    @Override
    public List<ServiceEntity> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        ServiceEntity service = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        repo.delete(service); // bookings will cascade delete
    }
}
