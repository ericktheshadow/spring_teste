package br.com.erick.forum.security

import br.com.erick.forum.model.Usuario
import br.com.erick.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutenticacaoService(
    private val repository : UsuarioRepository

) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario: Optional<Usuario>? = username?.let { repository.findByEmail(it) }
        if (usuario != null) {
            if (usuario.isPresent){
                return usuario.get()
            }
        }
        throw UsernameNotFoundException("Dados invalidos")
    }
}