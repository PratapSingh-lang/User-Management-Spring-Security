package com.alibou.security.auth;

import com.alibou.security.config.JwtService;
import com.alibou.security.service.CustomUserDetails;
import com.alibou.security.service.UserService;
import com.alibou.security.token.Role;
import com.alibou.security.token.RoleRepository;
import com.alibou.security.token.Token;
import com.alibou.security.token.TokenRepository;
import com.alibou.security.token.TokenType;
//import com.alibou.security.user.Role;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RoleRepository roleRepository; 
  private final UserService userService;

  private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
  
  
  public User register(RegisterRequest request) {
	  var user = User.builder()
		        .firstname(request.getFirstname())
		        .lastname(request.getLastname())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
//		        .role(Role.USER)
//		        .role(listOfRole)
		        .build();
    
    var savedUser = repository.save(user);
    log.warn("saved user is {}", savedUser);
    
    List<Role> listOfRole = new ArrayList<>();
	  Role role = new Role();
	  role.setName("USER");
	  role.setUser(savedUser);
	  listOfRole.add(role);
	  
	  roleRepository.save(role);
//    var savedUserWithRole =
//    		saveUserRole(savedUser, role);
//	  var user = 
//	    		userService.loadUserByUsername(request.getEmail());
	  
	    log.info(" user is {}", user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(user, jwtToken);
    User user2 = repository.findByEmail(user.getEmail()).orElse(null);
    List<Role> roleUser = roleRepository.findByUserId(user2.getId());
    List<Token> findAllValidTokenByUser = tokenRepository.findAllValidTokenByUser(user2.getId());
    user2.setRole(roleUser);
    user2.setTokens(findAllValidTokenByUser);
    return user2;
    
//    return AuthenticationResponse.builder()
//        .token(jwtToken)
//        .build();
  }

  private void saveUserRole(User savedUser, Role role) {
//	  var token = Token.builder()
//		        .user(user)
//		        .token(jwtToken)
//		        .tokenType(TokenType.BEARER)
//		        .expired(false)
//		        .revoked(false)
//		        .build();
//		    tokenRepository.save(token);
		    
		   var r =  Role.builder()
				   .user(savedUser)
				   .name(role.getName())
				   .build();
		   Role save = roleRepository.save(r);
	  

	  
}

public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = 
//    		userService.loadUserByUsername(request.getEmail());
    		repository.findByEmail(request.getEmail())
        .orElseThrow();
    
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
//        .role(user.getRole())
        .user(user)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
	  User u1 = (User) user;
    var token = Token.builder()
        .user(u1)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
  
  public List<User> getAllUsers() {
	  return repository.findAll();
  }
  
}
