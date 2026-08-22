package com.example.junitdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.junitdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    // Custom query using JPQL with index parameters
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    // Custom query using JPQL with named parameters
    @Query("select e from Employee e where e.firstName =:firstName and e.lastName =:lastName")
    Employee findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // Custom query using native SQL with index parameters
    @Query(value = "select * from employees e where e.first_name =?1 and e.last_name =?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);
}
