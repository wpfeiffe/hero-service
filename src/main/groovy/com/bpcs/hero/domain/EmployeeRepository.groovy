package com.bpcs.hero.domain

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring Data JPA repository for the Employee entity.
 */
@SuppressWarnings("unused")
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{
    List<Employee> findByDepartment(Department department)

}
