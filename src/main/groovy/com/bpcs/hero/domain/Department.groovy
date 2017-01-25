package com.bpcs.hero.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.io.Serializable
import java.util.Objects

/**
 * A Department.
 */
@Entity
@Table(name = "department")
class Department implements Serializable {

    private static final long serialVersionUID = 1L

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", allocationSize = 1)
    private Long id

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "dept_code", length = 20, nullable = false)
    private String deptCode

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "dept_name", length = 30, nullable = false)
    private String deptName

    @ManyToOne
    private Company company

     Long getId() {
        return id
    }

     void setId(Long id) {
        this.id = id
    }

     String getDeptCode() {
        return deptCode
    }

     void setDeptCode(String deptCode) {
        this.deptCode = deptCode
    }

     String getDeptName() {
        return deptName
    }

     void setDeptName(String deptName) {
        this.deptName = deptName
    }

     Company getCompany() {
        return company
    }

     void setCompany(Company company) {
        this.company = company
    }

    @Override
     boolean equals(Object o) {
        if (this == o) {
            return true
        }
        if (o == null || getClass() != o.getClass()) {
            return false
        }
        Department department = (Department) o
        if(department.id == null || id == null) {
            return false
        }
        return Objects.equals(id, department.id)
    }

    @Override
     int hashCode() {
        return Objects.hashCode(id)
    }

    @Override
     String toString() {
        return "Department{" +
            "id=" + id +
            ", deptCode='" + deptCode + "'" +
            ", deptName='" + deptName + "'" +
            '}'
    }
}
