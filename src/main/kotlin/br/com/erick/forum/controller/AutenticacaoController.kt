package br.com.erick.forum.controller

import br.com.erick.forum.dto.LoginForm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AutenticacaoController {

    @PostMapping
    fun autenticar(@RequestBody @Valid form : LoginForm):ResponseEntity<Any>{
        println(form.getEmail())
        println(form.getSenha())

        return ResponseEntity.ok().build()
    }
}