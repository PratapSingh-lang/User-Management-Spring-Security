package com.alibou.security.user;

import java.io.Serializable;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class Httpservelet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response ;
    HttpServletRequest request;
    FilterChain filterChain;
    
//    String response ;
//    String request;
//    String filterChain;
}
