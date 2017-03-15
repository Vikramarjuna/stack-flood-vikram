package com.cisco.cmad.stackflood.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWSTokenUtils {
	private static final String JWT_KEY = PropertiesUtil.get("jwt_key");
	
	public static String createToken(String userId,long ttMillis) {  
		//Get current timestamp
		long nowMillis = System.currentTimeMillis();  
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setIssuedAt(new Date(nowMillis)).setSubject(userId)  
		  .setIssuer("MyAuthoritativeService")
		  .signWith(SignatureAlgorithm.HS256, JWT_KEY)
		  .setExpiration(new Date(System.currentTimeMillis()+ttMillis));
		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();  
		}
	public static String parseToken(String token){
		Jws<Claims> claims = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token);  
		String userId = claims.getBody().getSubject();//Subject is user ID in this example.  
		Object email = claims.getBody().get("email"); 
		return userId;
	}
}
