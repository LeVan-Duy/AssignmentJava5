package com.leduy.backend.repository;

import com.leduy.backend.entity.ShopOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder,String> {
    @Query("SELECT x FROM ShopOrder x WHERE :search IS NULL OR x.fullName LIKE  CONCAT('%', :search, '%')")
    Page<ShopOrder> paginationShopOrder(Pageable pageable, String search);
}
