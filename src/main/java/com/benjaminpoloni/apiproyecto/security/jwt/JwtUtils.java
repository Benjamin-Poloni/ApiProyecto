package com.benjaminpoloni.apiproyecto.security.jwt;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {


    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;




    // creacion del token

    public String generateAccesToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignaturegKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    // obtener signature

    public Key getSignaturegKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Validamos token de acceso

    public  boolean tokenValid(String token) {
        try {
            String correctToken = token.trim();
            Jwts.parserBuilder().setSigningKey(getSignaturegKey()).build().parseClaimsJws(correctToken).getBody();
            return true;
        }catch (Exception e) {
            log.error("El token es invalido, error".concat(e.getMessage()));
           return false;
        }
    }

    // obtener todos los claims del token

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignaturegKey()).build().parseClaimsJws(token).getBody();
    }



    // obtener un solo claim

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver ) {
            Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
    }

    // obtener username del token

    public String getSubject(String token) {
        return getClaim(token, Claims::getSubject);
    }
}
