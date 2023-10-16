package com.leduy.backend.repository;

import com.leduy.backend.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,String> {
    @Query("SELECT x FROM ProductDetail x WHERE :search IS NULL OR x.product.name LIKE  CONCAT('%', :search, '%')")
    Page<ProductDetail> paginationProductDetail(Pageable pageable, String search);
}
