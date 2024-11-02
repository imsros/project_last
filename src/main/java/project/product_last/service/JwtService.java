package project.product_last.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    //Generate a JWT token
    public String generateToken(int id){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(id+"")
                .setIssuedAt(new Date)
                .setExpiration(new Date(now.getTime() + 3600 * 1000)) //1hour
                .signWith(SignatureAlgorithm.ES256, secretKey.getBytes()) //Ensure the key is correctly handle
                .compact();
    }
    //Extract claims from a JWT token
    public Claims extractClaim(String token){
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();

    }
    //Extract username from a JWT token
    public String extractId(String token){
        return extractClaim(token).getSubject();
    }
    //Check if the JWT token is expired
    public boolean isTokenExpired(String token){
        return extractClaim(token).getExpiration().before(new Date());
    }
    //Validate the JWT token
    public boolean validateToken(String token, UserDetails userDetails){
        //Compare the extracted username with the username from UserDetails
        return (userDetails.getUsername().equals(extractId(token) && !isTokenExpired(token)));
    }
}
