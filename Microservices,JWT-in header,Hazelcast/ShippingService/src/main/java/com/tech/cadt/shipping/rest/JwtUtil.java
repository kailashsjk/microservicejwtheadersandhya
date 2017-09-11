package com.tech.cadt.shipping.rest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JwtUtil {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    
	public static String getSubject(HttpServletResponse httpServletResponse,String token, String signingKey) {
		
		LOGGER.info("JWTUtil Service Started::::::");
		LOGGER.info("Inside getSubject() method::::");
		Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
		String username = claims.getBody().getSubject();
		LOGGER.info("Subject :::"+username);
		return username;
	}

	public static String getPassword(HttpServletResponse httpServletResponse,String token, String signingKey) {
		LOGGER.info("JWTUtil Service Started::::::");
		LOGGER.info("Inside getPassword() method::::");
		Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
		Object password = claims.getBody().get("password");
		LOGGER.info("password :::"+password);
		return password.toString();
	}


	}
