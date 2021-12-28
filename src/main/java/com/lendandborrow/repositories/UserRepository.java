package com.lendandborrow.repositories;

import com.lendandborrow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    //TODO: never use the entity, always use optional and check if present,
    // otherwise throw entity not found exception

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

}
