package com.cbt.cbtjan24;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductofferstatusRepository extends JpaRepository<Productofferstatus, String>
{

    List<Productofferstatus> findByStatusIgnoreCase(String status);

    @Transactional
    @Modifying
    @Query("update Productofferstatus p set p.status = ?1 where p.offerid = ?2")
    int updateStatusByOfferid(String status, String offerid);
}