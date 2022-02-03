package br.com.erick.forum.dto

import br.com.erick.forum.controller.AutenticacaoController
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

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

        return BCryptPasswordEncoder().encode(this.senha)
    }

    fun converter(): UsernamePasswordAuthenticationToken {
        return UsernamePasswordAuthenticationToken(email,BCryptPasswordEncoder().encode(this.senha))
    }

}
