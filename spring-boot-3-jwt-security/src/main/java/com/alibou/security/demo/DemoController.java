package com.alibou.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibou.security.auth.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {

	  private final AuthenticationService service;
  @GetMapping("/hello")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }
  
  @GetMapping("/getallusers")
  public ResponseEntity<?> getallusers() {
    return ResponseEntity.ok(service.getAllUsers());
  }
  
  @GetMapping("/getadminapi")
  @Secured("ADMIN")
  public ResponseEntity<?> getadminapi() {
    return ResponseEntity.ok("this api is accessible to users having admin role");
  }
  
  @GetMapping("/getuserapi")
  @Secured("USER")
  public ResponseEntity<?> getuserapi() {
    return ResponseEntity.ok("this api is accessible to users having user role");
  }

}
