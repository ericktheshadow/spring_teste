package br.com.erick.forum.repository

import br.com.erick.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository: JpaRepository<Usuario,Long> {

    fun findByEmail(email:String?) : Optional<Usuario>
}