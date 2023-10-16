package com.leduy.backend.repository;

import com.leduy.backend.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Color,String> {
}
