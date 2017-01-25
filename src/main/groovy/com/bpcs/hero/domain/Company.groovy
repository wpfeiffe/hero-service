package com.bpcs.hero.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
class Company implements Serializable {

    private static final long serialVersionUID = 1L

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id

    @NotNull
    @Size(min = 5, max = 60)
    @Column(name = "company_name", length = 60, nullable = false)
    private String companyName

     Long getId() {
        return id
    }

     void setId(Long id) {
        this.id = id
    }

     String getCompanyName() {
        return companyName
    }

     void setCompanyName(String companyName) {
        this.companyName = companyName
    }

    @Override
     boolean equals(Object o) {
        if (this == o) {
            return true
        }
        if (o == null || getClass() != o.getClass()) {
            return false
        }
        Company company = (Company) o
        if(company.id == null || id == null) {
            return false
        }
        return Objects.equals(id, company.id)
    }

    @Override
     int hashCode() {
        return Objects.hashCode(id)
    }

    @Override
     String toString() {
        return "Company{" +
            "id=" + id +
            ", companyName='" + companyName + "'" +
            '}'
    }
}
