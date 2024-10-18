package com.ECommerceApplication.service.impl;

import java.util.Optional;

import com.ECommerceApplication.config.UserInfoConfig;
import com.ECommerceApplication.entity.User;
import com.ECommerceApplication.exception.ResourceNotFoundException;
import com.ECommerceApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByEmail(username);

		return user.map(UserInfoConfig::new).orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
	}
}