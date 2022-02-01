package br.com.erick.forum.mapper

import br.com.erick.forum.dto.TopicoView
import br.com.erick.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<Topico, TopicoView> {
    override fun map(it: Topico): TopicoView {
        return TopicoView(
            id = it.id,
            titulo = it.titulo,
            mensagem = it.mensagem,
            dataCriacao = it.dataCriacao,
            status = it.status
        )
    }

}