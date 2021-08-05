package ru.goodex.web.jwt;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.goodex.web.entity.Roles;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long validationTimeInSeconds;
    @Value("${jwt.serviceExpiration}")
    private Long serviceValidationTimeInSeconds;

    @Value("${jwt.serviceUsername}")
    private String serviceUsername;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, Set<Roles> role, UUID uuid) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        claims.put("id", uuid);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validationTimeInSeconds * 1000);

        return buildJwt(claims, now, validity);
    }


    public String createServiceToken() {
        Claims claims = Jwts.claims().setSubject(serviceUsername);
        Date now = new Date();
        Date validity = new Date(now.getTime() + serviceValidationTimeInSeconds * 1000);
        return buildJwt(claims, now, validity);
    }

    private String buildJwt(Claims claims, Date now, Date validity) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
