package br.com.erick.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm(
    @field:NotEmpty(message = "Não pode deixar em branco")
    @Size(min = 5, max = 140) val titulo: String = "",
    @field:NotEmpty val mensagem: String = "",
    @field:NotNull val idCurso: Long = 0,
    @field:NotNull val idAutor: Long = 0
)
