package com.example.api_gateway.services;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtils {

    private final SecretKey secretKey;

    public JwtUtils() {
        String secret = "XZJVERVevwMLOofmwemfwVMERINEIMWOEMFWOEFM34T34653e3RTBn2232323ewfeIVNRIRG3FO9i9g34OKMHMKLQP3233j3onfwdEKVWEFMWkwvwew";
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isExpired(String token){
        try{
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e){
            return true;
        }
    }

    public Integer extractUserId(String token){
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}
