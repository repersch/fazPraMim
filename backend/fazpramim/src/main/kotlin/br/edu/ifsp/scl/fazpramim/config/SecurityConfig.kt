package br.edu.ifsp.scl.fazpramim.config

import br.edu.ifsp.scl.fazpramim.security.jwt.JwtConfigurer
import br.edu.ifsp.scl.fazpramim.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val tokenProvider: JwtTokenProvider
){

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        val encoders: MutableMap<String, PasswordEncoder> = HashMap()
        val pbkdf2Encoder = Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
        encoders["pbkdf2"] = pbkdf2Encoder
        val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)
        return passwordEncoder
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic().disable()
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .authorizeHttpRequests {
                it.requestMatchers("/", "home", "/person").permitAll()
                it.anyRequest().authenticated()
            }
            .formLogin {
                it.loginPage("/login")
                it.permitAll()
            }
            .cors()
            .and()
            .apply(JwtConfigurer(tokenProvider))


        return http.build()
    }




//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http {
//            authorizeRequests {
//                authorize("/greetings/**", hasAuthority("ROLE_ADMIN"))
//                authorize("/**", permitAll)
//            }
//            httpBasic {}
//            return http.build();
//        }
//    }



//    @Bean
//    fun authenticationManagerBean(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
//        return authenticationConfiguration.authenticationManager
//    }
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        return http
//            .httpBasic().disable()
//            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
//            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
//                session.sessionCreationPolicy(
//                    SessionCreationPolicy.STATELESS
//                )
//            }
//            .authorizeHttpRequests {
//                    authorizeHttpRequests -> authorizeHttpRequests
//                .requestMatchers(
//                    "/person/signin",
//                    "/swagger-ui/**"
//                ).permitAll()
//                .requestMatchers("/api/**").authenticated()
//                .requestMatchers("/users").denyAll()
//            }
//            .cors()
//            .and()
//            .apply(JwtConfigurer(tokenProvider))
//            .and()
//            .build()
//    }
}

