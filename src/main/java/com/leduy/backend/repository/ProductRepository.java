package com.leduy.backend.repository;

import com.leduy.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    @Query("SELECT x FROM Product x WHERE :search IS NULL OR x.name LIKE  CONCAT('%', :search, '%')")
    Page<Product> paginationProduct(Pageable pageable, String search);
}
