package br.com.erick.forum.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    //Configurações de autenticação
    override fun configure(auth: AuthenticationManagerBuilder?) {

    }

    //Configurações de Autorização
    override fun configure(http: HttpSecurity?) {
        http?.authorizeHttpRequests()?.
        antMatchers(HttpMethod.GET,"/topicos")?.permitAll()?.
        antMatchers(HttpMethod.GET,"/topicos/*")?.permitAll()
    }

    //Configurações de recursos estaticos(js,css,imagens,etc)
    override fun configure(web: WebSecurity?) {

    }
}