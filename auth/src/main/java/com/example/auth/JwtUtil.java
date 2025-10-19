package com.example.auth;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
  private Long TOKEN_AGE = Long.valueOf(3600000);
  private String SECRET = "my-super-secret-key-my-super-secret-key";

  // public String createToken(UserDetails user) throws Exception {
  // var claims = new HashMap<String, Object>();
  // claims.put("role", ((User) user).getRole());

  // var now = System.currentTimeMillis();

  // return Jwts.builder()
  // .claims()
  // .subject(user.getUsername())
  // .add(claims)
  // .and()
  // .signWith(keyUtil.getPrivateKey())
  // .issuedAt(new Date(now))
  // .expiration(new Date(now + TOKEN_AGE))
  // .compact();
  // }

  private SecretKey getSigningKey() {
    byte[] decodedKey = Base64.getDecoder().decode(SECRET);
    return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
  }

  public String generateToken(String username) {
    var claims = new HashMap<String, Object>();
    claims.put("role", "ADMIN");

    return Jwts.builder()
        .subject(username)
        .claims()
        .add(claims)
        .and()
        .signWith(getSigningKey())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + TOKEN_AGE))
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(getSigningKey())
          .build()
          .parse(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
