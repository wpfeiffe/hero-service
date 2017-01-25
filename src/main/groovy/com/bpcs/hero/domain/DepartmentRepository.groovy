package com.bpcs.hero.domain

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring Data JPA repository for the Department entity.
 */
@SuppressWarnings("unused")
public interface DepartmentRepository extends JpaRepository<Department,Long>
{

}
