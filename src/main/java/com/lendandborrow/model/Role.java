package com.lendandborrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lendandborrow.model.enums.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private EnumRole name;

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }
}
