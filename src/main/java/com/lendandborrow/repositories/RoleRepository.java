package com.lendandborrow.repositories;

import com.lendandborrow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   //TODO: never use the entity, always use optional and check if present,
   // otherwise throw entity not found exception

   Optional<Role> findByName(String name);

   Role findById(long id);

}
