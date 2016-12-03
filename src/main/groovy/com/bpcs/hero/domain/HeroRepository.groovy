package com.bpcs.hero.domain

import com.bpcs.hero.domain.Hero
import org.springframework.data.jpa.repository.JpaRepository

/**
 * JPA Repo for Hero
 */
interface HeroRepository extends JpaRepository<Hero, Long>
{
    Optional<Hero> findByName(String hero);

}
