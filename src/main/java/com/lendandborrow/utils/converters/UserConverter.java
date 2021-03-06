package com.lendandborrow.utils.converters;

import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static UserDTO convertUserToUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .roles(user.getRoles())
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password("********")
                .build();
    }

    public static User convertUserDTOToUser(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .roles(userDTO.getRoles())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

}
