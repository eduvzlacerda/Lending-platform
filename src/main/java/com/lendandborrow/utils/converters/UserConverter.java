package com.lendandborrow.utils.converters;

import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class UserConverter {

    public static UserDTO convertUserToUserDTO(User user){
        return UserDTO.builder()
                .articles(user.getArticles())
                .roles(user.getRoles())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static User convertUserDTOToUser(UserDTO userDTO){
        return User.builder()
                .articles(userDTO.getArticles())
                .roles(userDTO.getRoles())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

}
