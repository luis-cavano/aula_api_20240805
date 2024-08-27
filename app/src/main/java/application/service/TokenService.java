package application.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import application.model.Usuario;

@Service
public class TokenService {
    private String secret = "123456789";

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API Tarefas")
                .withSubject(usuario.getNomeDeUsuario())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
                
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o JWT", exception);
        }
    }
    
    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("API Tarefas")
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado.");
        }
    }
}
