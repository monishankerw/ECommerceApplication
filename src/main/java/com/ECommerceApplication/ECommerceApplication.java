package com.ECommerceApplication;

import com.ECommerceApplication.config.AppConstants;
import com.ECommerceApplication.entity.Role;
import com.ECommerceApplication.repository.RoleRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@SecurityScheme(name = "E-Commerce Application", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class ECommerceApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();
			adminRole.setRoleId(AppConstants.ADMIN_ID);
			adminRole.setRoleName("ADMIN");

			Role userRole = new Role();
			userRole.setRoleId(AppConstants.USER_ID);
			userRole.setRoleName("USER");

			List<Role> roles = List.of(adminRole, userRole);

			List<Role> savedRoles = roleRepo.saveAll(roles);

			savedRoles.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
