package com.cbt.cbtjan24;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderstatusRepository extends JpaRepository<Orderstatus, String> {
    Optional<Orderstatus> findByStatusIgnoreCase(String status);

    @Transactional
    @Modifying
    @Query("update Orderstatus o set o.status = ?1 where o.orderid = ?2")
    int updateStatusByOrderid(String status, String orderid);
}