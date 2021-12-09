package com.lendandborrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lendandborrow.model.enums.EnumRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private EnumRole name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();


    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }
}
