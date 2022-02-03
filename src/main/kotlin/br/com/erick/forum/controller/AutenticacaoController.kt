package br.com.erick.forum.controller

import br.com.erick.forum.dto.LoginForm
import br.com.erick.forum.dto.TokenDto
import br.com.erick.forum.security.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.naming.AuthenticationException
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AutenticacaoController(
    @Autowired
    private val authManager: AuthenticationManager,

    @Autowired
    private var tokenService : TokenService
) {
    @PostMapping
    fun autenticar(@RequestBody @Valid form: LoginForm): ResponseEntity<TokenDto> {
        println(form.getEmail())
        println(form.getSenha())

        var dadosLogin: UsernamePasswordAuthenticationToken = form.converter()

        try {
            var authentication = authManager.authenticate(dadosLogin)
            println("passou por aqui aaa")
            var token :String = tokenService.gerarToken(authentication)
            println("passou por aqui tbm")
            return ResponseEntity.ok(TokenDto(token,"Bearer"))//.build()
        }catch (e : AuthenticationException){
            println("Passou por aqui")
            return ResponseEntity.badRequest().build()
        }catch (e: Exception){
            println("passou por aqui no exception")
            println(e.toString())
            return ResponseEntity.badRequest().build()
        }

    }
}