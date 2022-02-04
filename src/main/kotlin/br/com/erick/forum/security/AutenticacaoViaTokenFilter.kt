package br.com.erick.forum.security

import br.com.erick.forum.model.Usuario
import br.com.erick.forum.repository.UsuarioRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticacaoViaTokenFilter : OncePerRequestFilter {
    private val tokenService: TokenService
    private val repository : UsuarioRepository

    constructor(tokenService: TokenService, repository: UsuarioRepository) : super() {
        this.tokenService = tokenService
        this.repository = repository
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String = recuperarToken(request)
        val valido: Boolean = tokenService.isTokenValido(token)
        //println(token)
        //println(valido)
        if(valido){
            autenticarCliente(token)
        }
        filterChain.doFilter(request, response)
    }

    private fun autenticarCliente(token: String) {
        val idUsuario:Long=tokenService.getIdUsuario(token)
        val usuario : Usuario = repository.findById(idUsuario).get()
        val autentication = UsernamePasswordAuthenticationToken(usuario,null,usuario.authorities)
        SecurityContextHolder.getContext().authentication = autentication
    }

    private fun recuperarToken(request: HttpServletRequest): String {
        val token = request.getHeader("Authorization")
        if (token == null || token.isEmpty() || token.startsWith("Bearer ")) {
            return null.toString()
        }
        return token.substring(7, token.length)
    }
}