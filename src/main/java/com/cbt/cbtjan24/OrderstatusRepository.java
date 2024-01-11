package com.cbt.cbtjan24;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderstatusRepository extends JpaRepository<Orderstatus, String> {
    Optional<Orderstatus> findByStatusIgnoreCase(String status);
}