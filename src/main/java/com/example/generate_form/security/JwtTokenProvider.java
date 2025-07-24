package com.example.generate_form.security;

import com.example.generate_form.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  private final JwtProperties jwtProperties;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
  }

  public String generateToken(Authentication authentication) {
    UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
    Date expiryDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration());

    return Jwts.builder()
        .subject(userPrincipal.getUsername())
        .issuedAt(new Date())
        .expiration(expiryDate)
        .signWith(getSigningKey())
        .compact();
  }

  public String generateTokenFromEmail(String email) {
    Date expiryDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration());

    return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(expiryDate)
        .signWith(getSigningKey())
        .compact();
  }

  public String getEmailFromJWT(String token) {
    Claims claims =
        Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();

    return claims.getSubject();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }
}
