package br.com.erick.forum.dto

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
        return this.senha
    }

}
