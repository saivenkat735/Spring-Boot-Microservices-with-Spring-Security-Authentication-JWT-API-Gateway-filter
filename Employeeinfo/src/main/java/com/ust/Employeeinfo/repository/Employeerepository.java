package com.ust.Employeeinfo.repository;

import com.ust.Employeeinfo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Employeerepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByCcode(Long ccode);
}
