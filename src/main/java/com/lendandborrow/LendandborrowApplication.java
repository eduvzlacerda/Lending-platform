package com.lendandborrow;

import com.lendandborrow.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Import({SwaggerConfig.class})
public class LendandborrowApplication {
    //TODO : re-do dummie data to account for changes
    public static void main(String[] args) {
        SpringApplication.run(LendandborrowApplication.class, args);
    }

    //TODO: move this to SecurityConfig
    //Beans should always be in a config file ,
    // the application file should be clean and only have the main method
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
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
