package com.alibou.security.auth;

import java.util.List;

import com.alibou.security.token.Role;
import com.alibou.security.user.User;

//import com.alibou.security.user.Role;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String token;
//  private List<Role> role;
  private User user;
}
