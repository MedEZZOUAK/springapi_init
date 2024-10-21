package ensate.ma.SpringAPI.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  private static final String signingKey ="Q23FZ5IfxCdXKDYFGMDjvXUpj6uKce72DR2/hvW7z4mq3REpFHc391qsoXBUZect";

  public <T> T extractClaim(String jwt, Function <Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwt);
    return claimsResolver.apply(claims);
  }

  public String extractEmail(String jwt) {
    return extractClaim(jwt, Claims::getSubject);
  }

  private Claims extractAllClaims(String jwt) {
  return Jwts.parserBuilder().setSigningKey(getsigningKey()).build().parseClaimsJws(jwt).getBody();
}

  private Key getsigningKey() {
    byte[] signingKeyBytes = Base64.getDecoder().decode(signingKey);
    return Keys.hmacShaKeyFor(signingKeyBytes);
  }
  public String generateToken(Map<String, Object> extraclaims, UserDetails userDetails) {
    return Jwts
            .builder()
            .setClaims(extraclaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 *60*10 ))
            .signWith(getsigningKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
            .compact();
  }
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(0), userDetails);
  }
  public Boolean isTokenValid(String jwt, UserDetails userDetails) {
    final String email = extractEmail(jwt);
    return (email.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
  }
  private Boolean isTokenExpired(String jwt) {
    return extractExpiration(jwt).before(new Date());
  }
  private Date extractExpiration(String jwt) {
    return extractClaim(jwt, Claims::getExpiration);
  }




}
