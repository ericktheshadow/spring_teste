package br.com.erick.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TesteJpaApplication

fun main(args: Array<String>) {
	runApplication<TesteJpaApplication>(*args)
}
