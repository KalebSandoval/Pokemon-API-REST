package sandoval.brian.pokeApi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sandoval.brian.pokeApi.dto.UserPermission;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    // PAYLOAD
    //sub: subject
    //iat: issued at
    //exp: cuando expira

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String user, String role) {
        return Jwts.builder()
                .subject(user)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigninKey())
                .claim("role", role)
                .compact();
    }

    public UserPermission extractUser(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        UserPermission user = new UserPermission();
        user.setEmail(claims.getSubject());
        user.setRole(claims.get("role").toString());

        return user;
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigninKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
