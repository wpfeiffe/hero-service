package com.bpcs.hero.rest

import com.bpcs.hero.domain.Department
import com.bpcs.hero.domain.DepartmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Department rest controller
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/departments")
class DepartmentRestController
{

    private final DepartmentRepository departmentRepository;

    @Autowired
    DepartmentRestController(DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Department>> getDepartmentes()
    {

        List<Department> departments = departmentRepository.findAll()
        if (departments.isEmpty())
        {
            return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Department> addDepartment(@RequestBody Department input)
    {
        Optional<Department> departments = departmentRepository.findByName(input.name)

        if (departments.isPresent())
        {
            return new ResponseEntity<Department>(HttpStatus.CONFLICT)
        }
        Department resultDepartment = departmentRepository.save(new Department(input.name))
        return new ResponseEntity<Department>(resultDepartment, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{departmentId}")
    ResponseEntity<Department> updateDepartment(@RequestBody Department input, @PathVariable Long departmentId)
    {
        Department department = departmentRepository.findOne(departmentId)

        if (!department)
        {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND)
        }

        department.name = input.name
        Department resultDepartment = departmentRepository.save(department)
        return new ResponseEntity<Department>(resultDepartment, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.GET, value="/{departmentId}")
    ResponseEntity<Department> getDepartment(@PathVariable Long departmentId)
    {
        Department department = departmentRepository.findOne(departmentId)

        if (!department)
        {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND)
        }
        return new ResponseEntity<Department>(department, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{departmentId}")
    ResponseEntity<Department>  removeDepartment(@PathVariable Long departmentId)
    {
        Department department = departmentRepository.findOne(departmentId)

        if (!department)
        {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND)
        }
        departmentRepository.delete(departmentId)
        return new ResponseEntity<Department>(department, HttpStatus.OK)
    }
}
