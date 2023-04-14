package com.alibou.security.token;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value = "select * from Role where user_id=:id", nativeQuery=true)
	List<Role> findByUserId(Integer id);

}
