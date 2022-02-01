package br.com.erick.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AtualizacaoTopicoForm(
    @field:NotNull
    val id:Long,
    @field:NotEmpty
    @field:Size(min = 5, max = 140)
    val titulo:String,
    @NotEmpty
    val mensagem:String
)
