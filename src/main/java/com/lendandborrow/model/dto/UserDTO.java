package com.lendandborrow.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lendandborrow.model.Article;
import com.lendandborrow.model.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class UserDTO {

    @NotNull
    private String name;

    private String password;
    @NotNull
    private String email;
    @NotNull
    private Set<Role> roles;
    @JsonIgnore
    private Set<Article> articles;
}
