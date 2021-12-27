package com.lendandborrow.repositories;

import com.lendandborrow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   //TODO: never use the entity, always use optional and check if present,
   // otherwise throw entity not found exception

   //should not be needed (@Query line), but it doesn`t work without it right now,
   //although the behaviour should be exactly the same, apparently it is not
   @Query(value = "SELECT * FROM Roles r WHERE r.name LIKE ?1", nativeQuery = true)
   Optional<Role> findByName(String name);

   Role findById(long id);

}
