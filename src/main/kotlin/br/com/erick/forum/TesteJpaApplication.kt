package br.com.erick.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.web.config.EnableSpringDataWebSupport
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
//@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
class TesteJpaApplication

fun main(args: Array<String>) {
	runApplication<TesteJpaApplication>(*args)
}
