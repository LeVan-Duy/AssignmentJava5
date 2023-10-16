package com.leduy.backend.repository;

import com.leduy.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Controller
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    Optional<Employee>findByEmail(String email);

}
