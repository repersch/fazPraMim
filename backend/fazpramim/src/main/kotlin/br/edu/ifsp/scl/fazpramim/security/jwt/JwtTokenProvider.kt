package br.edu.ifsp.scl.fazpramim.security.jwt

import br.edu.ifsp.scl.fazpramim.controller.response.LoginResponse
import br.edu.ifsp.scl.fazpramim.exception.InvalidJwtAuthenticationException
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@Service
class JwtTokenProvider(
    private val userDetailsService: UserDetailsService
) {

    @Value("\${security.jwt.token.secret-key:secret}")
    private var secretKey = "secret"

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityInMilliseconds: Long = 3_600_000L

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(username: String, roles: List<String?>): LoginResponse {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        val accessToken = getAccessToken(username, roles, now, validity)
        val refreshToken = getRefreshToken(username, roles, now)
        return LoginResponse(
            email = username,
            authenticated = true,
            accessToken = accessToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity
        )
    }

    private fun getRefreshToken(username: String, roles: List<String?>, now: Date): String {
        val validityRefreshToken = Date(now.time + validityInMilliseconds * 3)
        return JWT.create()
            .withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(username)
            .sign(algorithm)
            .trim()
    }

    private fun getAccessToken(username: String, roles: List<String?>, now: Date, validity: Date): String {
        val issuerURL: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(username)
            .withIssuer(issuerURL)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token: String): Authentication {
        val decodedJwt: DecodedJWT = decodedToken(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(decodedJwt.subject)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun decodedToken(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer")) {
            bearerToken.substring("Bearer ".length)
        } else null
    }

    fun validateToken(token: String): Boolean {
        val decodedJWT = decodedToken(token)
        try {
            if(decodedJWT.expiresAt.before(Date())) false
            return true
        } catch (ex: Exception) {
            throw InvalidJwtAuthenticationException("Token JWT inválido ou expirado.")
        }

    }

}
