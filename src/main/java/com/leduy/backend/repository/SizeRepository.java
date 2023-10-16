package com.leduy.backend.repository;

import com.leduy.backend.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size,String> {
    @Query("SELECT x FROM Size x WHERE :search IS NULL OR x.name LIKE  CONCAT('%', :search, '%')")
    Page<Size> paginationSize(Pageable pageable, String search);
}
