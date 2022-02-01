package br.com.erick.forum.mapper

import br.com.erick.forum.dto.NovoTopicoForm
import br.com.erick.forum.model.Topico
import br.com.erick.forum.service.AutorService
import br.com.erick.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val autorService: AutorService,
    private var topicosForm: List<Topico> = ArrayList()

    ) : Mapper<NovoTopicoForm,Topico> {

    fun pegaTopicoForm(topicoForm:List<Topico>){
          this.topicosForm=topicoForm
    }
    override fun map(dto: NovoTopicoForm): Topico {
        return Topico(
            id = topicosForm.size.toLong() + 1,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = autorService.buscarPorId(dto.idAutor)
        )
    }
}
