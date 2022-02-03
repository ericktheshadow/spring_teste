package br.com.erick.forum.service

import br.com.erick.forum.model.Usuario
import br.com.erick.forum.repository.UsuarioRepository
import br.com.erick.forum.security.AutenticacaoService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutorService(private val repository: UsuarioRepository)  {

    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }


}
/*   init {
       val usuario = Usuario(
           id = 1,
           nome = "kotlin",
           email = "erick@gmail.com"
       )
       this.usuario = Arrays.asList(usuario)
   }

   fun buscarPorId(id: Long): Usuario {
       return this.usuario.stream().filter({ it ->
           it.id == id
       }).findFirst().get()
   }
*/