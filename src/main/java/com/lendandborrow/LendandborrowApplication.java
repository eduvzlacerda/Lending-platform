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

}
