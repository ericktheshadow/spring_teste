package br.com.erick.forum.service

import br.com.erick.forum.model.Curso
import br.com.erick.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso {
        return repository.getOne(id)
    }

}

/* init {
      val curso = Curso(
          id = 1,
          nome = "kotlin",
          categoria = "corinthianus"
      )
      cursos = Arrays.asList(curso)
  }

  fun buscarPorId(id: Long): Curso {
      return cursos.stream().filter({ it ->
          it.id == id
      }).findFirst().get()
  }*/
