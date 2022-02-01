package br.com.erick.forum.service

import br.com.erick.forum.dto.AtualizacaoTopicoForm
import br.com.erick.forum.dto.NovoTopicoForm
import br.com.erick.forum.dto.TopicoPorCategoriaDto
import br.com.erick.forum.dto.TopicoView
import br.com.erick.forum.exception.NotFoundException
import br.com.erick.forum.mapper.TopicoFormMapper
import br.com.erick.forum.mapper.TopicoViewMapper
import br.com.erick.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico não encontrado"
) {

    /*init {
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = Curso(
                id = 1,
                nome = "kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Erick",
                email = "erick@gmail.com"
            )
        )
        topicos = Arrays.asList(topico, topico, topico)
    }*/

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = if(nomeCurso == null){
            repository.findAll(paginacao)
        }else{
            repository.findByCursoNome(nomeCurso,paginacao)
        }
        return topicos.map { it ->
            topicoViewMapper.map(it)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(dto)
        //topico.id = topicos.size.toLong() + 1
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        /* val topicoAtualizado =  Topico(
             id = form.id,
             titulo = form.titulo,
             mensagem = form.mensagem,
             autor = topico.autor,
             curso = topico.curso,
             respostas = topico.respostas,
             status = topico.status,
             dataCriacao = topico.dataCriacao
         )
         topicos = topicos.minus(topico).plus(topicoAtualizado)*/
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
       /* val topico = topicos.stream().filter { it ->
            it.id == id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }
        topicos = topicos.minus(topico).plus(topico)*/
    }

    fun relatorio():List<TopicoPorCategoriaDto>{
        return repository.relatorio()
    }


}