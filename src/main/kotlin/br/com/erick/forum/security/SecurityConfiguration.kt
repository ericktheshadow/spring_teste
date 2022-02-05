package br.com.erick.forum.security

import br.com.erick.forum.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    @Autowired
    private val autenticacaoService: AutenticacaoService,
    @Autowired
    private val tokenService: TokenService,
    @Autowired
    private val usuarioRepository: UsuarioRepository

) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    //Configurações de autenticação
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(autenticacaoService)?.passwordEncoder(bCryptPasswordEncoder())
        // println(auth?.userDetailsService(autenticacaoService)?.passwordEncoder(bCryptPasswordEncoder()))
    }

    //Configurações de Autorização
    override fun configure(http: HttpSecurity?) {
        http?.authorizeHttpRequests()?.antMatchers(HttpMethod.GET, "/topicos")?.permitAll()
            ?.antMatchers(HttpMethod.GET, "/topicos/*")?.permitAll()
            ?.antMatchers(HttpMethod.POST, "/topicos")?.permitAll()?.
            antMatchers(HttpMethod.POST, "/auth")?.permitAll()?.
            antMatchers(HttpMethod.GET, "/actuator/**")?.permitAll()?.
            antMatchers(HttpMethod.DELETE, "/topicos/*")?.hasRole("MODERADOR")?.
            anyRequest()?.authenticated()?.
            and()?.csrf()
            ?.disable()?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()?.addFilterBefore(AutenticacaoViaTokenFilter(tokenService,usuarioRepository),UsernamePasswordAuthenticationFilter().javaClass)
        //and()?.formLogin()
    }

    //Configurações de recursos estaticos(js,css,imagens,etc)
    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")

    }
}