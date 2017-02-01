package com.bpcs.hero.service

import com.bpcs.hero.domain.Company
import com.bpcs.hero.domain.CompanyRepository
import com.bpcs.hero.domain.Department
import com.bpcs.hero.domain.DepartmentRepository
import com.bpcs.hero.domain.Employee
import com.bpcs.hero.domain.EmployeeRepository
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import javax.inject.Inject;

/**
 * Provides the company first hierarchal set of data
 */
@Service
@Transactional
class CompanyNodeService
{
    @Inject
    CompanyRepository companyRepository

    @Inject
    DepartmentRepository departmentRepository

    @Inject
    EmployeeRepository employeeRepository

    List<TreeNodeDTO> getCompanyNodes()
    {
        // get companies and create company node list
        List<Company> companyList = companyRepository.findAll()
        List<TreeNodeDTO> companyNodes = []


        // loop the companies
        companyList.each { Company company ->

            // create the current company node
            TreeNodeDTO nodeCompany = new TreeNodeDTO()
            nodeCompany.label = company.companyName
            companyNodes << nodeCompany


            nodeCompany.children = getDepartmentNodes(company)
            nodeCompany.collapsedIcon = "fa-folder"
            nodeCompany.expandedIcon = "fa-folder-open"
            nodeCompany.data = [id: company.id, type: "Company"]

        }

        return companyNodes
    }

    List<TreeNodeDTO> getDepartmentNodes(Company company)
    {
        // get departments for company
        List<Department> departmentList = departmentRepository.findByCompany(company)
        List<TreeNodeDTO> departmentNodes = []

        if (departmentList && departmentList.size() > 0)
        {

            departmentList.each { Department department ->

                // create the current dept node
                TreeNodeDTO nodeDepartment = new TreeNodeDTO()
                nodeDepartment.label = department.deptName
                departmentNodes << nodeDepartment

                nodeDepartment.children = getEmployeeNodes(department)
                nodeDepartment.collapsedIcon = "fa-folder"
                nodeDepartment.expandedIcon = "fa-folder-open"
                nodeDepartment.data = [id: department.id, type: "Department"]

            }
        }
        return departmentNodes
    }

    List<TreeNodeDTO> getEmployeeNodes(Department department)
    {
        // get empoyees for department
        List<Employee> employeeList = employeeRepository.findByDepartment(department)
        List<TreeNodeDTO> employeeNodes = []

        if (employeeList && employeeList.size() > 0)
        {

            employeeList.each { Employee employee ->

                // create the current dept node
                TreeNodeDTO nodeEmployee = new TreeNodeDTO()
                nodeEmployee.label = String.format("%s %s (%s)", employee.firstName, employee.lastName, employee.title)
                nodeEmployee.icon = "fa-user"
                employeeNodes << nodeEmployee
                nodeEmployee.data = [id: employee.id, type: "Employee"]
            }
        }
        return employeeNodes
    }


}
