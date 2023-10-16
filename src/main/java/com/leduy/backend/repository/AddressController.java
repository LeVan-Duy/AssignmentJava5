package com.leduy.backend.repository;

import com.leduy.backend.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressController extends JpaRepository<Address,String> {
    @Query("SELECT x FROM Address x WHERE :search IS NULL OR x.city LIKE  CONCAT('%', :search, '%')")
    Page<Address> paginationBrand(Pageable pageable, String search);
}
