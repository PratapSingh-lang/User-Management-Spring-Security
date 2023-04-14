package com.alibou.security.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  
  @Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email = ?1")
  Optional<User> findByUsernameWithRoles(String username);


}
