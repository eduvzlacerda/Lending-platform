package com.lendandborrow.model.dto;

import com.lendandborrow.model.Role;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
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
