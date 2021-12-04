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
}
