package br.com.erick.forum.dto

class TokenDto(
     var token: String,
     var tipo: String
) {
    fun TokenDto(token: String, tipo: String) {
        this.token=token
        this.tipo=tipo
            }
    fun getToken(token: String){
        this.token=token
    }
    fun getTipo(tipo: String){
        this.tipo=tipo
    }
}
