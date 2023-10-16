package com.leduy.backend.repository;

import com.leduy.backend.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,String> {
    @Query("SELECT x FROM Brand x WHERE :search IS NULL OR x.name LIKE  CONCAT('%', :search, '%')")
    Page<Brand> paginationBrand(Pageable pageable, String search);
}
