package com.alibou.security.user;

import com.alibou.security.token.Role;
import com.alibou.security.token.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User 
//implements UserDetails
{

  @Id
  @GeneratedValue
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;

//  @Enumerated(EnumType.STRING)
//  private Role role;
  
  @Default
  @OneToMany(
//		  fetch = FetchType.LAZY,
		  mappedBy = "user")
  private List<Role> role= new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
  

//  public User() {
//      this.role = new ArrayList<>(); // Initialize the collection with an empty list
//  }

//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//
//	  List<Role> roles = this.role;
//	    if (roles == null) {
//	        return Collections.emptySet();
//	    }
//
//	    return roles.stream()
//	            .map(role -> new SimpleGrantedAuthority(role.getName()))
//	            .collect(Collectors.toSet());
	  
	  
//	  List<GrantedAuthority> authorities = new ArrayList<>();
//
//	    for (Role r : role) {
//	        authorities.add(new SimpleGrantedAuthority(r.getName()));
//	    }
	    
	    
//	    authorities.add(new SimpleGrantedAuthority());
//	  role.forEach(r -> r.getRole());

//		return authorities;
	  
//	      return List.of(new SimpleGrantedAuthority(
//	    		  
////	    		  role.name()
////	    		  role.forEach(r -> r.getRole());
//	    		  
////	    		  role.get(0).toString()
//	    		  
//	    		  ));
//  }

//  @Override
//  public String getPassword() {
//    return password;
//  }
//
//  @Override
//  public String getUsername() {
//    return email;
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
}
