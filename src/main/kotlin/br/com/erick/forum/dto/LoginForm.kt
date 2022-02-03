package br.com.erick.forum.dto

import br.com.erick.forum.controller.AutenticacaoController
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class LoginForm(

    private var email: String,
    private var senha: String
) {
    fun setMail(email: String) {
        this.email = email
    }

    fun setSenha(email: String) {
        this.senha = senha
    }

    fun getEmail(): String {
        return this.email
    }

    fun getSenha(): String {

        return this.senha
    }

    fun converter(): UsernamePasswordAuthenticationToken {
        println(UsernamePasswordAuthenticationToken(email,senha))
        return UsernamePasswordAuthenticationToken(email,senha)
    }

}
