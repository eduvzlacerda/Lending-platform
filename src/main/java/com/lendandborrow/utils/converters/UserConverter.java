package com.lendandborrow.utils.converters;

import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static UserDTO convertUserToUserDTO(User user){
        return UserDTO.builder()
                .roles(user.getRoles())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static User convertUserDTOToUser(UserDTO userDTO){
        return User.builder()
                .roles(userDTO.getRoles())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

}
