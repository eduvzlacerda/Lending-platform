package com.lendandborrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lendandborrow.model.dto.UserDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Builder(toBuilder = true)
public class User {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    @NonNull
    @NotNull
    private String name;

    @Column(name = "password")
    @NonNull
    @NotNull
    private String password;

    @Column(name = "email", unique=true)
    @NonNull
    @NotNull
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable
    @NonNull
    @NotNull
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Article> articles = new HashSet<>();


}
