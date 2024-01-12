package com.cbt.cbtjan24;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductofferRepository extends JpaRepository<Productoffer, String>
{
    List<Productoffer> findBySellernameIgnoreCase(String sellername);
}