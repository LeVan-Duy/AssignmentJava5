package com.leduy.backend.repository;

import com.leduy.backend.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query("SELECT x FROM Customer x WHERE :search IS NULL OR x.fullName LIKE  CONCAT('%', :search, '%')")
    Page<Customer> paginationCustomer(Pageable pageable, String search);
}
