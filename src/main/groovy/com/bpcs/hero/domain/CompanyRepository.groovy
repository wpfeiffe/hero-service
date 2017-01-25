package com.bpcs.hero.domain

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring Data JPA repository for the Company entity.
 */
@SuppressWarnings("unused")
public interface CompanyRepository extends JpaRepository<Company,Long>
{

}
