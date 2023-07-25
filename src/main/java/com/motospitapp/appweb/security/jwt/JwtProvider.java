package com.motospitapp.appweb.security.jwt;

import com.motospitapp.appweb.model.entities.user.UserMain;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserMain userMain = (UserMain) authentication.getPrincipal();

        return Jwts.builder().setSubject(userMain.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration *1000))
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token malformed");
        } catch (UnsupportedJwtException e) {
            logger.error("Token not supported");
        } catch (ExpiredJwtException e) {
            logger.error("Token expired");
        } catch (IllegalArgumentException e) {
            logger.error("Token empty");
        }
        return false;
    }
}
