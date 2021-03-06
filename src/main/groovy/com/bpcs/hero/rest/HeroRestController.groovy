package com.bpcs.hero.rest

import com.bpcs.hero.domain.Hero
import com.bpcs.hero.domain.HeroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Hero rest controller
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/heroes")
class HeroRestController
{

    private final HeroRepository heroRepository;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate

    @Autowired
    HeroRestController(HeroRepository heroRepository)
    {
        this.heroRepository = heroRepository
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Hero>> getHeroes()
    {

        List<Hero> heroes = heroRepository.findAll()
        if (heroes.isEmpty())
        {
            return new ResponseEntity<List<Hero>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Hero>>(heroes, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Hero> addHero(@RequestBody Hero input)
    {
        Optional<Hero> heroes = heroRepository.findByName(input.name)

        if (heroes.isPresent())
        {
            return new ResponseEntity<Hero>(HttpStatus.CONFLICT)
        }
        Hero resultHero = heroRepository.save(new Hero(input.name))

        this.brokerMessagingTemplate.convertAndSend("/topic/hero", [action: "Add", hero: resultHero])

        return new ResponseEntity<Hero>(resultHero, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{heroId}")
    ResponseEntity<Hero> updateHero(@RequestBody Hero input, @PathVariable Long heroId)
    {
        Hero hero = heroRepository.findOne(heroId)

        if (!hero)
        {
            return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND)
        }

        hero.name = input.name
        Hero resultHero = heroRepository.save(hero)
        this.brokerMessagingTemplate.convertAndSend("/topic/hero", [action: "Update", hero: resultHero])
        return new ResponseEntity<Hero>(resultHero, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.GET, value="/{heroId}")
    ResponseEntity<Hero> getHero(@PathVariable Long heroId)
    {
        Hero hero = heroRepository.findOne(heroId)

        if (!hero)
        {
            return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND)
        }
        this.brokerMessagingTemplate.convertAndSend("/topic/hero", [action: "Get", hero: hero])
        return new ResponseEntity<Hero>(hero, HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{heroId}")
    ResponseEntity<Hero>  removeHero(@PathVariable Long heroId)
    {
        Hero hero = heroRepository.findOne(heroId)

        if (!hero)
        {
            return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND)
        }
        heroRepository.delete(heroId)
        this.brokerMessagingTemplate.convertAndSend("/topic/hero", [action: "Delete", hero: hero])
        return new ResponseEntity<Hero>(hero, HttpStatus.OK)
    }
}
