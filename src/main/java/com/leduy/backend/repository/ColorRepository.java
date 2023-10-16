package com.leduy.backend.repository;

import com.leduy.backend.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {

    @Query("SELECT x FROM Color x WHERE :search IS NULL OR x.name LIKE  CONCAT('%', :search, '%')")
    Page<Color> paginationColor(Pageable pageable, String search);
}
