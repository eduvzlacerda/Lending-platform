package com.lendandborrow;

import com.lendandborrow.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SwaggerConfig.class})
public class LendandborrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendandborrowApplication.class, args);
	}
/*
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
						user.setPassword(new BCryptPasswordEncoder().encode("password"));
						userRepository.save(user);

			});

			userRepository.findAll().forEach(user -> userDataService.setUserRole(EnumRole.ADMIN, user.getId()));

			//userRepository.findAll().forEach(System.out::println);

			//System.out.print("Finish!");

		};

	}
*/
}
