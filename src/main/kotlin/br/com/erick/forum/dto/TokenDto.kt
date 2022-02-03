package br.com.erick.forum.dto

class TokenDto(
    private var token: String,
    private var tipo: String
) {
    fun TokenDto(token: String, tipo: String) {
        this.token=token
        this.tipo=tipo
    }
}
