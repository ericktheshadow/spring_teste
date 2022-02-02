package br.com.erick.forum.security

import br.com.erick.forum.model.Usuario
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TokenService(

    @Value("\${forum.jwt.expiration}")
    private var expiration : String,

    @Value("\${forum.jwt.secret}")
    private var secret : String,

) {
    fun gerarToken(authentication: Authentication?): String {
        var logado : Usuario = authentication?.principal as Usuario
        var hoje : Date = Calendar.getInstance().time
        println(hoje)
        var dataExpiracao : Date = Date(hoje.time + expiration.toLong())
        return Jwts.builder().setIssuer("Erick Spring Teste").
        setSubject(logado.id.toString()).setIssuedAt(hoje).
                setExpiration(dataExpiracao).signWith(SignatureAlgorithm.ES256,secret).compact()
    }

}
