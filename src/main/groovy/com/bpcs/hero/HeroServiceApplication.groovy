package com.bpcs.hero

import com.bpcs.hero.domain.Hero
import com.bpcs.hero.domain.HeroRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class HeroServiceApplication {

	static void main(String[] args) {
		SpringApplication.run HeroServiceApplication, args
	}

	@Bean
	CommandLineRunner init(HeroRepository heroRepository)
	{
		return {
			evt ->
				List<String> starterNames = [
						"Mr. Nice",
						"Narco",
						"Bombasto",
						"Celeritas",
						"Magneta",
						"RubberMan",
						"Dynama",
						"Dr IQ",
						"Magma",
						"Tornado"
				]
				starterNames.each {heroName ->
					heroRepository.save(new Hero(heroName));
				}
		}
	}
}
