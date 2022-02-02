package br.com.erick.forum.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    @Autowired
    private val autenticacaoService: AutenticacaoService

) : WebSecurityConfigurerAdapter() {

    //Configurações de autenticação
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(autenticacaoService)?.passwordEncoder(BCryptPasswordEncoder())
        // println(auth?.userDetailsService(autenticacaoService)?.passwordEncoder(BCryptPasswordEncoder()))
    }

    //Configurações de Autorização
    override fun configure(http: HttpSecurity?) {
        http?.authorizeHttpRequests()?.antMatchers(HttpMethod.GET, "/topicos")?.permitAll()
            ?.antMatchers(HttpMethod.GET, "/topicos/*")?.
            permitAll()?.
            antMatchers(HttpMethod.POST, "/auth")?.permitAll()?.
            anyRequest()?.authenticated()?.
            and()?.csrf()
            ?.disable()?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //and()?.formLogin()
    }

    //Configurações de recursos estaticos(js,css,imagens,etc)
    override fun configure(web: WebSecurity?) {

    }
}