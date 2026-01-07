package com.alura.churnnsight.repository;

import com.alura.churnnsight.model.CustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerSessionRepository
        extends JpaRepository<CustomerSession, Long> {

    List<CustomerSession> findByCustomerId(Long customerId);
}

