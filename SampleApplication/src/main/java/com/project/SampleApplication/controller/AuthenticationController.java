package com.project.SampleApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.SampleApplication.jwt.JwtConfig;
import com.project.SampleApplication.jwt.JwtResponse;
import com.project.SampleApplication.model.RequestDetails;
import com.project.SampleApplication.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtConfig jwtConfig;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateuser(@RequestBody RequestDetails reqDetails) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(reqDetails.getUsername(), reqDetails.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtConfig.generateJwtToken(authentication);
		
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

}
