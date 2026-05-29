package com.example.auth_service_api.service.Implementation;

import com.example.auth_service_api.dtos.TokenResponse;
import com.example.auth_service_api.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService implements IJwtService {

    private final String secretToken;

    public JwtService(@Value("${jwt.secret}") String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public TokenResponse generateToken(UUID userId) {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretToken));

        String token = Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public Claims getClaims(String token) {

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretToken));

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public UUID extractedUserId(String token) {
        try{
          return UUID.fromString(getClaims(token).getSubject());
        }catch(Exception e){
            return null;
        }
    }
}
