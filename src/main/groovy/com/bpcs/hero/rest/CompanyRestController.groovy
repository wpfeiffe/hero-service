package com.bpcs.hero.rest

import com.bpcs.hero.domain.Company
import com.bpcs.hero.domain.CompanyRepository
import com.bpcs.hero.domain.Hero
import com.bpcs.hero.domain.HeroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Hero rest controller
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/companies")
class CompanyRestController
{

    private final CompanyRepository companyRepository;

    @Autowired
    CompanyRestController(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Company>> getHeroes()
    {

        List<Company> companies = companyRepository.findAll()
        if (companies.isEmpty())
        {
            return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Company>>(companies, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Company> addCompany(@RequestBody Company input)
    {
        Optional<Company> companies = companyRepository.findByName(input.name)

        if (companies.isPresent())
        {
            return new ResponseEntity<Company>(HttpStatus.CONFLICT)
        }
        Company resultCompany = companyRepository.save(new Company(input.name))
        return new ResponseEntity<Company>(resultCompany, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{companyId}")
    ResponseEntity<Company> updateCompany(@RequestBody Company input, @PathVariable Long companyId)
    {
        Company company = companyRepository.findOne(companyId)

        if (!company)
        {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND)
        }

        company.name = input.name
        Company resultCompany = companyRepository.save(company)
        return new ResponseEntity<Company>(resultCompany, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.GET, value="/{companyId}")
    ResponseEntity<Company> getCompany(@PathVariable Long companyId)
    {
        Company company = companyRepository.findOne(companyId)

        if (!company)
        {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND)
        }
        return new ResponseEntity<Company>(company, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{companyId}")
    ResponseEntity<Company>  removeCompany(@PathVariable Long companyId)
    {
        Company company = companyRepository.findOne(companyId)

        if (!company)
        {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND)
        }
        companyRepository.delete(companyId)
        return new ResponseEntity<Company>(company, HttpStatus.OK)
    }
}
