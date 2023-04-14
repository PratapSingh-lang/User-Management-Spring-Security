package com.alibou.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomUserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsernameWithRoles(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOptional.get();
//        return CustomUserDetails.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .roles(user.getRole())
//                .build(); 
        
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(user.getEmail());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setRoles(user.getRole());
        return customUserDetails;
    }
}
