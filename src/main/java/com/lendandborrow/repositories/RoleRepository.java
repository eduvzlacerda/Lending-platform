package com.lendandborrow.repositories;

import com.lendandborrow.model.Role;
import com.lendandborrow.model.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   //TODO: never use the entity, always use optional and check if present,
   // otherwise throw entity not found exception

   Role findByName(EnumRole role);

}
