package com.lendandborrow.model.dto;

import com.lendandborrow.model.Role;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserDTO {

    private UUID id;

    private String name;

    private String password;

    @NotNull
    private String email;

    private Set<Role> roles;

}
