package com.platzimaven.market.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.platzimaven.market.domain.dto.AuthenticationRequest;
import com.platzimaven.market.domain.dto.AuthenticationResponse;
import com.platzimaven.market.domain.service.PlatziUserDetailService;
import com.platzimaven.market.web.security.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PlatziUserDetailService platziUserDetailService;

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
		try {
			//Esto va llamar el loadUserByUsername de platziUserDetailService y 
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			UserDetails userDetails = this.platziUserDetailService.loadUserByUsername(request.getUserName());
			String jwt = this.jwtUtil.generateToken(userDetails);
			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt), HttpStatus.OK);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<AuthenticationResponse>(HttpStatus.FORBIDDEN);
		}

	}

}
