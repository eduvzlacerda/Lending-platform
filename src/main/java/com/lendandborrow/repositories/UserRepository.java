package com.lendandborrow.repositories;

import com.lendandborrow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //TODO: never use the entity, always use optional and check if present,
    // otherwise throw entity not found exception

    User findByEmail(String email);

    User findByName(String name);
}
