package com.ECommerceApplication.payload;

import com.ECommerceApplication.dto.UserDTO;
import lombok.Data;

@Data
public class JWTAuthResponse {
	private String token;
	
	private UserDTO user;
}