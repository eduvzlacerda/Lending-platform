package com.lendandborrow;

import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import com.lendandborrow.service.UserDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.relation.RoleResult;
import java.util.Collections;
import java.util.stream.Stream;

@SpringBootApplication
public class LendandborrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendandborrowApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
			UserRepository userRepository,
			RoleRepository roleRepository,
			UserDataService userDataService) {

		return args -> {

			Stream
					.of(EnumRole.values())
					.forEach(enumRole -> roleRepository.save(new Role(enumRole)));

			Stream
					.of("John", "Julie", "Jennifer", "Helen", "Rachel")
					.forEach(name -> {

						User user = new User(name, name.toLowerCase() + "@domain.com");
						userRepository.save(user);

			});

			userRepository.findAll().forEach(user -> userDataService.setUserRole(EnumRole.ADMIN, user.getId()));

			//userRepository.findAll().forEach(System.out::println);

			System.out.print("Finish!");

		};

	}

}
