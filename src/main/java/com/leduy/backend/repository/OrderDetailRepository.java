package com.leduy.backend.repository;

import com.leduy.backend.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    @Query("SELECT x FROM OrderDetail x WHERE :search IS NULL OR x.productDetail.product.name LIKE  CONCAT('%', :search, '%')")
    Page<OrderDetail> paginationOrderDetail(Pageable pageable, String search);
}
