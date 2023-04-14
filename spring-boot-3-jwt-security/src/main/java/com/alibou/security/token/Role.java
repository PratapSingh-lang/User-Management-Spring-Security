package com.alibou.security.token;

import java.util.List;

import com.alibou.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	
	  @Id
	  @GeneratedValue
	  private Integer id;
	  private String name;
	  private String description;
	  private boolean status;
	  
	  @ManyToOne
	  @JoinColumn(name = "user_id")
	  @JsonIgnore
	  public User user;
}
