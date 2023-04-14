package com.alibou.security.auth;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibou.security.config.JwtAuthenticationFilter;
import com.alibou.security.service.UserService;
import com.alibou.security.token.RoleRepository;
import com.alibou.security.token.TokenRepository;
import com.alibou.security.user.Httpservelet;
import com.alibou.security.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path ="/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final UserRepository repository;
  private final UserService userService;
  private final RoleRepository roleRepository;
  private final TokenRepository tokenRepository;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  @PostMapping("/register")
  public ResponseEntity<?> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
  
  @GetMapping("/deleteall")
  public ResponseEntity<?> deleteall(
//      @RequestBody AuthenticationRequest request
  ) {
	  
	  roleRepository.deleteAll();
	  tokenRepository.deleteAll();
	  repository.deleteAll();
    return ResponseEntity.ok("User Deleted");
  }
  
  @PostMapping("/freeApi")
  public ResponseEntity<?> freeApi(
//      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok("hi this is free api");
  }

//  , consumes = MediaType.APPLICATION_JSON_VALUE
  @PostMapping(path = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE)
  
	 public ResponseEntity<?> filter(
		      @RequestBody Httpservelet ht 
			 ) throws ServletException, IOException{
	  System.out.println("this is filter api of jwt");
//	  jwtAuthenticationFilter.doFilterInternal(ht.getRequest(), ht.getResponse(), ht.getFilterChain());
	System.out.println("doFilterInternal method executed");
    return ResponseEntity.ok("string"); 
  }
  
//  HttpServletRequest request,
//  @NonNull HttpServletResponse response,
//  @NonNull FilterChain filterChain
  
  @GetMapping("/validate")
  public 
//  UsernamePasswordAuthenticationToken
  boolean
  validateToken(
		  @RequestParam("token") String token
//		  @RequestParam("request") HttpServletRequest request
//		  ,@RequestParam("response") HttpServletResponse response
//		  ,@RequestParam("filterChain") FilterChain filterChain
		  ) throws ServletException, IOException {
//      service.validateToken(token);
//	  jwtAuthenticationFilter.doFilterInternal(ht.getRequest(), ht.getResponse(), ht.getFilterChain());
	  
//	  UsernamePasswordAuthenticationToken authenticationToken
	  
	 boolean status = jwtAuthenticationFilter.jwtvalidation(token);
	  System.out.println("inside validate api");
//	  jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
//	  jwtAuthenticationFilter.custom(request, response, filterChain);
//      return "Token is valid";
//	  return "get api call success";
//	  return authenticationToken;
	  return status;
  }
  public UserDetailsService userDetailsService() {
	    return username -> 
	    userService.loadUserByUsername(username);
//	    repository.findByEmail(username)
//	        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	  }
  
  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
  
  @GetMapping("/pro")
  public AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	  }
  
  @PostMapping("/validate")
  public String Token(
		  @RequestBody Httpservelet ht
		  ) throws ServletException, IOException {
//      service.validateToken(token);
	  jwtAuthenticationFilter.doFilterInternal(ht.getRequest(), ht.getResponse(), ht.getFilterChain());
	  System.out.println("inside validate post  api ");
//	  jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
//	  jwtAuthenticationFilter.custom(request, response, filterChain);
//      return "Token is valid";
	  return "hi this is valid";
  }
  
  
  

}
