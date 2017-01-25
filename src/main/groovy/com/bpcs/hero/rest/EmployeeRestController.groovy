package com.bpcs.hero.rest

import com.bpcs.hero.domain.Employee
import com.bpcs.hero.domain.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Employee rest controller
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
class EmployeeRestController
{

    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRestController(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Employee>> getEmployeees()
    {

        List<Employee> employees = employeeRepository.findAll()
        if (employees.isEmpty())
        {
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Employee> addEmployee(@RequestBody Employee input)
    {
        Optional<Employee> employees = employeeRepository.findByName(input.name)

        if (employees.isPresent())
        {
            return new ResponseEntity<Employee>(HttpStatus.CONFLICT)
        }
        Employee resultEmployee = employeeRepository.save(new Employee(input.name))
        return new ResponseEntity<Employee>(resultEmployee, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{employeeId}")
    ResponseEntity<Employee> updateEmployee(@RequestBody Employee input, @PathVariable Long employeeId)
    {
        Employee employee = employeeRepository.findOne(employeeId)

        if (!employee)
        {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND)
        }

        employee.name = input.name
        Employee resultEmployee = employeeRepository.save(employee)
        return new ResponseEntity<Employee>(resultEmployee, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.GET, value="/{employeeId}")
    ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId)
    {
        Employee employee = employeeRepository.findOne(employeeId)

        if (!employee)
        {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND)
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{employeeId}")
    ResponseEntity<Employee>  removeEmployee(@PathVariable Long employeeId)
    {
        Employee employee = employeeRepository.findOne(employeeId)

        if (!employee)
        {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND)
        }
        employeeRepository.delete(employeeId)
        return new ResponseEntity<Employee>(employee, HttpStatus.OK)
    }
}
