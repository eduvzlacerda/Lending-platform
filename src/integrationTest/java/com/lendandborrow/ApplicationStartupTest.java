package com.lendandborrow;

import com.lendandborrow.model.User;
import com.lendandborrow.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Sql(scripts = "classpath:/integration.sql")
public class ApplicationStartupTest extends CommonIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSqlInit() {

        User userFound = userRepository.findByName("Admin").orElseThrow();

        Assertions.assertEquals("admin@mail.com", userFound.getEmail());

    }


}
