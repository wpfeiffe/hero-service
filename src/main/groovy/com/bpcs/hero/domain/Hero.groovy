package com.bpcs.hero.domain

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id

/**
 * Hero jpa entity
 */
@Entity
class Hero
{


    @Id
    @GeneratedValue
    public Long id;

    public String name;


    public Hero(String name)
    {
        this.name = name;
    }

    Hero()
    { // jpa only
    }
}
