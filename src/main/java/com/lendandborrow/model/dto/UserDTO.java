package com.lendandborrow.model.dto;

import com.lendandborrow.model.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter @Setter
public class UserDTO {

    @NotNull
    private String name;

    private String password;

    @NotNull
    private String email;

    @NotNull
    private Set<Role> roles;

}
