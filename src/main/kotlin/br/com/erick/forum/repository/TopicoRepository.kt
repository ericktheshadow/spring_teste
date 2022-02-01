package br.com.erick.forum.repository

import br.com.erick.forum.dto.TopicoPorCategoriaDto
import br.com.erick.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico,Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>//List<Topico>

    @Query("select t from Topico t where t.respostas is empty")
    fun relatorio():List<TopicoPorCategoriaDto>
}