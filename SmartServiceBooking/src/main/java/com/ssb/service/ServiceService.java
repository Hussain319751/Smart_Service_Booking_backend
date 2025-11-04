package com.ssb.service;


import com.ssb.entity.ServiceEntity;
import java.util.List;

public interface ServiceService {
    ServiceEntity create(ServiceEntity service);
    ServiceEntity update(Long id, ServiceEntity service);
    ServiceEntity getById(Long id);
    List<ServiceEntity> getAll();
    void delete(Long id);
}
