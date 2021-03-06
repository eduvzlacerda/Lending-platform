package com.lendandborrow.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
//TODO: Add reference to articles ?

    @Id
    @NotNull
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email", unique=true)
    @NotNull
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
