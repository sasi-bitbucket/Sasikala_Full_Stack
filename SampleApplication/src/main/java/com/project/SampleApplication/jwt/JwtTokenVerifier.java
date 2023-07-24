package com.project.SampleApplication.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.SampleApplication.service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifier extends OncePerRequestFilter {
	@Autowired
	private JwtConfig jwtConfig;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenVerifier.class);
	
	private String jwtSecret = "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String headerAuth = request.getHeader("Authorization");

			if (!StringUtils.hasText(headerAuth) && !headerAuth.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			
			String token = headerAuth.replace("Bearer ", "");
			Jws<Claims> claimsJws = Jwts.parser()
					.setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
					.parseClaimsJws(token);
				
				Claims body = claimsJws.getBody();
				
				String username = body.getSubject();
				
				List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
				
				Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
													.map(m -> new SimpleGrantedAuthority(m.get("authority")))
													.collect(Collectors.toSet());
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,grantedAuthorities );
				SecurityContextHolder.getContext().setAuthentication(authentication );
			
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}
	
}
