package com.remproyectos.foro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.remproyectos.foro.domain.usuario.Usuario;
import com.remproyectos.foro.infra.exceptions.InvalidLoginException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String apiSecret;

    public String getSubject(String token) {
        if(token == null)
            throw new InvalidLoginException("Token invalido");
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("voll med")
                    // reusable verifier instance
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            System.out.println("Token no valido: " + exception);
        }
        if(verifier == null)
            throw new InvalidLoginException("Verifier inexsitente");
        if(verifier.getSubject() == "" || verifier.getToken() == null)
            throw new InvalidLoginException("Verifier no valido");
        return verifier.getSubject();
    }

    public String generarToken(Usuario usuario){
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new InvalidLoginException(exception);
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

}