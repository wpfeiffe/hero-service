package com.bpcs.hero.rest

import com.bpcs.hero.service.CounterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.inject.Inject

/**
 * Rest controller to enable/disable counter
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/counterctl")
class CounterRestController
{
    @Inject
    CounterService counterService

    @RequestMapping(value="/enable", method=RequestMethod.GET)
    ResponseEntity<String> enableCounter()
    {
        counterService.setEnabled(true);
        return new ResponseEntity<String>("Success", HttpStatus.OK)
    }


    @RequestMapping(value="/disable", method=RequestMethod.GET)
    ResponseEntity<String>  disableCounter()
    {
        counterService.setEnabled(false);
        return new ResponseEntity<String>("Success", HttpStatus.OK)
    }
}
