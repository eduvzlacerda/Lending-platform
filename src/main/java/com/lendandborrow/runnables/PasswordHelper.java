package com.lendandborrow.runnables;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHelper {

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "123456";

        String encoded = passwordEncoder.encode("123456");

        System.out.printf("Encoded password %s -> %s%n", password , encoded);

    }







}
