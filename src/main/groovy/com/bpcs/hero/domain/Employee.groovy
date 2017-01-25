package com.bpcs.hero.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.io.Serializable
import java.util.Objects


/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
class Employee implements Serializable {

    private static final long serialVersionUID = 1L

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    private Long id

    @NotNull
    @Size(min = 2, max = 40)
    @Column(name = "first_name", length = 40, nullable = false)
    private String firstName

    @NotNull
    @Size(min = 2, max = 40)
    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active

    @NotNull
    @Column(name = "start_date", nullable = false)
    private Date startDate

    @NotNull
    @Size(min = 8, max = 40)
    @Column(name = "title", length = 40, nullable = false)
    private String title

    @ManyToOne
    private Department department

     Long getId() {
        return id
    }

     void setId(Long id) {
        this.id = id
    }

     String getFirstName() {
        return firstName
    }

     void setFirstName(String firstName) {
        this.firstName = firstName
    }

     String getLastName() {
        return lastName
    }

     void setLastName(String lastName) {
        this.lastName = lastName
    }

     Boolean isActive() {
        return active
    }

     void setActive(Boolean active) {
        this.active = active
    }

     Date getStartDate() {
        return startDate
    }

     void setStartDate(Date startDate) {
        this.startDate = startDate
    }

     String getTitle() {
        return title
    }

     void setTitle(String title) {
        this.title = title
    }

     Department getDepartment() {
        return department
    }

     void setDepartment(Department department) {
        this.department = department
    }

    @Override
     boolean equals(Object o) {
        if (this == o) {
            return true
        }
        if (o == null || getClass() != o.getClass()) {
            return false
        }
        Employee employee = (Employee) o
        if(employee.id == null || id == null) {
            return false
        }
        return Objects.equals(id, employee.id)
    }

    @Override
     int hashCode() {
        return Objects.hashCode(id)
    }

    @Override
     String toString() {
        return "Employee{" +
            "id=" + id +
            ", firstName='" + firstName + "'" +
            ", lastName='" + lastName + "'" +
            ", active='" + active + "'" +
            ", startDate='" + startDate + "'" +
            ", title='" + title + "'" +
            '}'
    }
}
